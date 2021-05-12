package com.gof.springcloud.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gof.springcloud.constants.Constants;
import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.service.AppointmentService;
import com.gof.springcloud.vo.AppointmentDetail;
import com.gof.springcloud.vo.ResultVo;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 私募预约信息 前端控制器
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping
	@ApiOperation(value = "Get an appointment by key")
	public ResultVo<Appointment> getAppointment(int key){
		ResultVo<Appointment> resultVo = new ResultVo<Appointment>();
		Appointment appointment = appointmentService.getById(key);
		if (null == appointment) resultVo.failure(404, "appointment not found");
		else resultVo.success(appointment);
		return resultVo;
	}

	@GetMapping("/detail")
	@ApiOperation(value = "Get an appointment detail by key")
	public AppointmentDetail getDetail(int key) {
		return appointmentService.getDetail(key);
	}

	@PostMapping
	@ApiOperation(value = "Create an appointment")
	public ResultVo<Appointment> createAppointment(@Validated Appointment appointment,
			@RequestHeader(value = Constants.TOKEN_HEADER, required = false) String token) {
		ResultVo<Appointment> resultVo = new ResultVo<Appointment>();
		if (StringUtils.isBlank(token)) {
			resultVo.failure(401, "illegal request");
			return resultVo;
		}
		appointment.setAppointmentId(null);
		ResultVo<String> validateRes = appointmentService.validate(appointment);
		if (!validateRes.isSuccess()) {
			resultVo.failure(validateRes.getCode(), validateRes.getMsg());
			return resultVo;
		}
		return appointmentService.saveTransaction(appointment, token);
	}

	@PostMapping("/validate")
	@ApiOperation(value = "Validate an appointment")
	public ResultVo<String> validateAppointment(@Validated Appointment appointment) {
		return appointmentService.validate(appointment);
	}

	@PostMapping("/cancel")
	@ApiOperation(value = "Cancel an appointment")
	public ResultVo<String> cancelAppointment(@RequestParam int auditId, @RequestBody int key) {
		return appointmentService.cancel(auditId, key);
	}

}

