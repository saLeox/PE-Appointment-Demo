package com.gof.springcloud.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gof.springcloud.constants.Constants;
import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.entity.Availability;
import com.gof.springcloud.entity.Confirmschedule;
import com.gof.springcloud.entity.Product;
import com.gof.springcloud.feign.MockFeign;
import com.gof.springcloud.feign.vo.Client;
import com.gof.springcloud.feign.vo.Managerqualification;
import com.gof.springcloud.feign.vo.workflow.AuditDetailVo;
import com.gof.springcloud.mapper.AppointmentMapper;
import com.gof.springcloud.service.AppointmentService;
import com.gof.springcloud.service.ApprovalService;
import com.gof.springcloud.service.AvailabilityService;
import com.gof.springcloud.service.ConfirmscheduleService;
import com.gof.springcloud.service.ProductService;
import com.gof.springcloud.service.validator.Validator;
import com.gof.springcloud.service.validator.appointment.AppointmentValidator;
import com.gof.springcloud.service.validator.appointment.AvailabilityValidator;
import com.gof.springcloud.service.validator.appointment.ClientValidator;
import com.gof.springcloud.service.validator.appointment.ManagerValidator;
import com.gof.springcloud.vo.AppointmentDetail;
import com.gof.springcloud.vo.ResultVo;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 私募预约信息 服务实现类
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@Slf4j
@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {

	@Autowired
	private ClientValidator<String> clientValidator;
	@Autowired
	private ManagerValidator<String> managerValidator;
	@Autowired
	private AvailabilityValidator<String> availabilityValidator;
	@Autowired
	private AppointmentValidator<String> appointmentValidator;
	@Autowired
	private AvailabilityService availabilityService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ApprovalService approvalService;
	@Autowired
	private ConfirmscheduleService confirmscheduleService;
	@Autowired
	private MockFeign mockFeign;
	@Autowired
    private StringRedisTemplate redisTemplate;
	@Autowired
	private DefaultRedisScript<Boolean> defaultRedisScript;

	@Override
	public AppointmentDetail getDetail(int key) {
		// internal data
		Appointment appointment = this.getById(key);
		if (null == appointment) return null;
		Product product = productService.getById(appointment.getProductId());
		Availability availability = availabilityService.getOne(new QueryWrapper<Availability>().eq("product_id", appointment.getProductId()));
		Confirmschedule confirmschedule = confirmscheduleService.getOne(new QueryWrapper<Confirmschedule>().eq("appointment_id", key));
		// external data
		AuditDetailVo approvalDetail = approvalService.getApprovalDetail(appointment.getApprovalId());
		Client client = mockFeign.getClient(appointment.getClientId());
		Managerqualification managerqualification = mockFeign.getQualification(appointment.getManagerId());
		// pack the result
		AppointmentDetail res = AppointmentDetail.builder()
			.appointment(appointment)
			.product(product)
			.availability(availability)
			.confirmschedule(confirmschedule)
			.approvalDetail(approvalDetail)
			.client(client)
			.managerqualification(managerqualification)
			.build();
		return res;
	}

	@Override
	public ResultVo<String> validate(Appointment appointment) {
		List<Validator<String>> arr = new ArrayList<Validator<String>>();
		arr.add(clientValidator);
		arr.add(managerValidator);
		arr.add(availabilityValidator);
		arr.add(appointmentValidator);
		for (Validator<String> validator : arr) {
			ResultVo<String> resultVo = validator.validate(appointment);
			if (!resultVo.isSuccess()) return resultVo;
		}
		ResultVo<String> resultVo = new ResultVo<String>();
		resultVo.success(null);
		return resultVo;
	}


	/**
	 * @param appointment
	 * @param token
	 * @return
	  *  完整流程
	 * 0. alibaba哨兵 qps
	 * 1. REDIS
	 * 2. MQ - KAFKA
	 * 3. LISTNER -> MYSQL
	 * 4. Socket(Netty) -> APP
	 */
	@Override
	@Transactional
	public ResultVo<Appointment> saveTransaction(Appointment appointment, String token) {
		ResultVo<Appointment> resultVo = new ResultVo<Appointment>();
		Integer productId = appointment.getProductId();
		String fund_key = "availability_fund_" + productId;
		String investor_key = "availabilit_investor_" + productId;

		 //缓存查找
		String availability_fund = redisTemplate.opsForValue().get(fund_key);
		String availabilit_investor = redisTemplate.opsForValue().get(investor_key);

		// 判断是否为空
		if (availability_fund == null || availabilit_investor == null) {
			Availability availability = availabilityService
					.getOne(new QueryWrapper<Availability>().eq("product_id", appointment.getProductId()));
			// NX - > 存在则不更新，防止覆盖
			redisTemplate.opsForValue().setIfAbsent(fund_key, availability.getFundAvailability().toString());
			redisTemplate.opsForValue().setIfAbsent(investor_key, availability.getInvestorAvailability().toString());
		}

		// 判断库存
        List<String> keys = Arrays.asList(productId.toString(), appointment.getSubscribeQty().toString());
        Boolean execute = redisTemplate.execute(defaultRedisScript, keys);
		if (execute == false) {
			log.info("{}: 库存不足", productId);
			resultVo.failure(500, "产品库存不足");
			return resultVo;
		}
		// 更新库存
		availabilityService.updateAvailability(appointment.getSubscribeQty());

		// 初始化
		int approvalId = approvalService.startProcess(token, appointment.getClientId(), appointment.getProductId());

		// 生成预约
		appointment.setSubscribeTime(new Date());
		appointment.setApprovalId(approvalId);
		appointment.setStatus(Constants.APPOINTMENT_STATUS_PENDING);
		this.save(appointment);

		resultVo.success(appointment);
		return resultVo;
	}


//	@Override
//	@Transactional
//	public ResultVo<Appointment> saveTransaction(Appointment appointment, String token) {
//		// availability update
//		ResultVo<Appointment> resultVo = new ResultVo<Appointment>();
//		Availability availability = availabilityService
//				.getOne(new QueryWrapper<Availability>().eq("product_id", appointment.getProductId()));
//		if (null == availability) {
//			Product product = productService.getById(appointment.getProductId());
//			availability = Availability.builder()
//					.productId(appointment.getProductId())
//					.investorAvailability(product.getInvestorLimit() - 1)
//					.fundAvailability(product.getFundCapacity() - appointment.getSubscribeQty())
//					.build();
//			availabilityService.save(availability);
//		} else {
//			availability.setInvestorAvailability(availability.getInvestorAvailability() - 1);
//			availability.setFundAvailability(availability.getFundAvailability()- appointment.getSubscribeQty());
//			availabilityService.updateById(availability);
//		}
//		// initiate workflow
//		int approvalId = approvalService.startProcess(token, appointment.getClientId(), appointment.getProductId());
//		// make appointment
//		appointment.setSubscribeTime(new Date());
//		appointment.setApprovalId(approvalId);
//		appointment.setStatus(Constants.APPOINTMENT_STATUS_PENDING);
//		this.save(appointment);
//		resultVo.success(appointment);
//		return resultVo;
//	}

	@Override
	@Transactional
	public ResultVo<String> cancel(int approvalId, int key) {
		ResultVo<String> resultVo = new ResultVo<String>();
		// validate
		ResultVo<Appointment> validateRes = this.legalValidate(approvalId, key);
		if (!validateRes.isSuccess()) {
			resultVo.failure(validateRes.getCode(), validateRes.getMsg());
			return resultVo;
		}
		Appointment appointment = validateRes.getObj();
		Availability availability = availabilityService
				.getOne(new QueryWrapper<Availability>().eq("product_id", appointment.getProductId()));
		if (null == availability) {
			resultVo.failure(400, "Illegal Request");
			return resultVo;
		}
		appointment.setStatus(Constants.APPOINTMENT_STATUS_FAILURE);
		this.updateById(appointment);
		// return availability
		availability.setFundAvailability(availability.getFundAvailability() + appointment.getSubscribeQty());
		availability.setInvestorAvailability(availability.getInvestorAvailability() + 1);
		availabilityService.updateById(availability);
		resultVo.success(null);
		return resultVo;
	}

	@Override
	public ResultVo<Appointment> legalValidate(int approvalId, int appointmentId) {
		ResultVo<Appointment> resultVo = new ResultVo<Appointment>();
		Appointment appointment = this.getById(appointmentId);
		if (null == appointment || !appointment.getApprovalId().equals(approvalId)
				|| !appointment.getStatus().equals(Constants.APPOINTMENT_STATUS_PENDING)) {
			resultVo.failure(400, "Illegal Request");
			return resultVo;
		}
		resultVo.success(appointment);
		return resultVo;
	}


}
