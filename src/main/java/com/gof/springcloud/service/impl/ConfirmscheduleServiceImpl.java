package com.gof.springcloud.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gof.springcloud.constants.Constants;
import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.entity.Availability;
import com.gof.springcloud.entity.Confirmschedule;
import com.gof.springcloud.entity.Product;
import com.gof.springcloud.mapper.ConfirmscheduleMapper;
import com.gof.springcloud.rabbit.DelayedSender;
import com.gof.springcloud.service.AppointmentService;
import com.gof.springcloud.service.AvailabilityService;
import com.gof.springcloud.service.ConfirmscheduleService;
import com.gof.springcloud.service.ProductService;
import com.gof.springcloud.utils.TimeUtils;
import com.gof.springcloud.vo.ResultVo;

/**
 * <p>
 * 用户确认计划 服务实现类
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@Service
public class ConfirmscheduleServiceImpl extends ServiceImpl<ConfirmscheduleMapper, Confirmschedule> implements ConfirmscheduleService {

	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private ProductService productService;
	@Autowired
	private AvailabilityService availabilityService;
	@Autowired
	private DelayedSender delayedSender;

	@Override
	public ResultVo<String> schedule(int approvalId, int key) {
		ResultVo<String> resultVo = new ResultVo<String>();
		// validate
		ResultVo<Appointment> validateRes = appointmentService.legalValidate(approvalId, key);
		if (!validateRes.isSuccess()) {
			resultVo.failure(validateRes.getCode(), validateRes.getMsg());
			return resultVo;
		}
		// save the schedule
		Appointment appointment = validateRes.getObj();
		Product product = productService.getById(appointment.getProductId());
		Date coolingStart = new Date();
		Date coolingEnd = TimeUtils.delayHour(coolingStart, product.getCoolingPeriod());
		Confirmschedule schedule = Confirmschedule.builder().appointmentId(key).coolingStart(coolingStart)
				.coolingEnd(coolingEnd).build();
		this.save(schedule);
		// send the confirm key to delayed queue to execute the task
		delayedSender.delayedMessage(schedule.getScheduleId().toString(),
				TimeUtils.getMilliSecondGap(coolingStart, coolingEnd));
		resultVo.success(null);
		return resultVo;
	}

	@Override
	@Transactional
	public ResultVo<String> failure(int approvalId, int key) {
		ResultVo<String> resultVo = new ResultVo<String>();
		// validate
		ResultVo<Appointment> validateRes = appointmentService.legalValidate(approvalId, key);
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
		Confirmschedule confirmschedule = this.getOne(new QueryWrapper<Confirmschedule>().eq("appointment_id", key));
		if (null == confirmschedule) {
			resultVo.failure(400, "Illegal Request");
			return resultVo;
		}
		// update appointment status
		appointment.setStatus(Constants.APPOINTMENT_STATUS_FAILURE);
		appointmentService.updateById(appointment);
		// update availability and return quota
		availability.setFundAvailability(availability.getFundAvailability() + appointment.getSubscribeQty());
		availability.setInvestorAvailability(availability.getInvestorAvailability() + 1);
		availabilityService.updateById(availability);
		// update callback result
		confirmschedule.setCallbackTime(new Date());
		confirmschedule.setResult(0);
		this.updateById(confirmschedule);
		// return
		resultVo.success(null);
		return resultVo;
	}

	@Override
	@Transactional
	public ResultVo<String> success(int approvalId, int key) {
		ResultVo<String> resultVo = new ResultVo<String>();
		// validate
		ResultVo<Appointment> validateRes = appointmentService.legalValidate(approvalId, key);
		if (!validateRes.isSuccess()) {
			resultVo.failure(validateRes.getCode(), validateRes.getMsg());
			return resultVo;
		}
		// update appointment status
		Appointment appointment = validateRes.getObj();
		appointment.setStatus(Constants.APPOINTMENT_STATUS_SUCCESS);
		appointmentService.updateById(appointment);
		// update callback result
		Confirmschedule confirmschedule = this.getOne(new QueryWrapper<Confirmschedule>().eq("appointment_id", key));
		confirmschedule.setCallbackTime(new Date());
		confirmschedule.setResult(1);
		this.updateById(confirmschedule);
		// return
		resultVo.success(null);
		return resultVo;
	}

}
