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
	public ResultVo<Appointment> saveTransaction(Appointment appointment) {
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
		int auditID = 1;
		// make appointment
		appointment.setSubscribeTime(new Date());
		appointment.setApprovalId(auditID);
		appointment.setStatus(Constants.APPOINTMENT_STATUS_PENDING);
		this.save(appointment);
		resultVo.success(appointment);
		return resultVo;
	}

}
