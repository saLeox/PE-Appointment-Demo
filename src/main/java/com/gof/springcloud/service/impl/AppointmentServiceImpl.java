package com.gof.springcloud.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gof.springcloud.constants.Constants;
import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.entity.Availability;
import com.gof.springcloud.entity.Product;
import com.gof.springcloud.mapper.AppointmentMapper;
import com.gof.springcloud.service.AppointmentService;
import com.gof.springcloud.service.ApprovalService;
import com.gof.springcloud.service.AvailabilityService;
import com.gof.springcloud.service.ProductService;
import com.gof.springcloud.service.validator.Validator;
import com.gof.springcloud.service.validator.appointment.AppointmentValidator;
import com.gof.springcloud.service.validator.appointment.AvailabilityValidator;
import com.gof.springcloud.service.validator.appointment.ClientValidator;
import com.gof.springcloud.service.validator.appointment.ManagerValidator;
import com.gof.springcloud.vo.ResultVo;

/**
 * <p>
 * 私募预约信息 服务实现类
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
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

	@Override
	@Transactional
	public ResultVo<Appointment> saveTransaction(Appointment appointment, String token) {
		// availability update
		ResultVo<Appointment> resultVo = new ResultVo<Appointment>();
		Availability availability = availabilityService
				.getOne(new QueryWrapper<Availability>().eq("product_id", appointment.getProductId()));
		if (null == availability) {
			Product product = productService.getById(appointment.getProductId());
			availability = Availability.builder()
					.productId(appointment.getProductId())
					.investorAvailability(product.getInvestorLimit() - 1)
					.fundAvailability(product.getFundCapacity() - appointment.getSubscribeQty())
					.build();
			availabilityService.save(availability);
		} else {
			availability.setInvestorAvailability(availability.getInvestorAvailability() - 1);
			availability.setFundAvailability(availability.getFundAvailability()- appointment.getSubscribeQty());
			availabilityService.updateById(availability);
		}
		// initiate workflow
		int approvalId = approvalService.startProcess(token, appointment.getClientId(), appointment.getProductId());
		// make appointment
		appointment.setSubscribeTime(new Date());
		appointment.setApprovalId(approvalId);
		appointment.setStatus(Constants.APPOINTMENT_STATUS_PENDING);
		this.save(appointment);
		resultVo.success(appointment);
		return resultVo;
	}

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
	public ResultVo<String> finalise(int approvalId, int key) {
		ResultVo<String> resultVo = new ResultVo<String>();
		// validate
		ResultVo<Appointment> validateRes = this.legalValidate(approvalId, key);
		if (!validateRes.isSuccess()) {
			resultVo.failure(validateRes.getCode(), validateRes.getMsg());
			return resultVo;
		}
		Appointment appointment = validateRes.getObj();
		appointment.setStatus(Constants.APPOINTMENT_STATUS_SUCCESS);
		this.updateById(appointment);
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
