package com.gof.springcloud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.entity.ResultVo;
import com.gof.springcloud.service.AppointmentService;

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

	@PostMapping
	@ApiOperation(value = "Create an appointment")
	public ResultVo<Appointment> createAppointment(Appointment appointment) {
		appointment.setAppointmentId(null);
		appointmentService.save(appointment);
		ResultVo<Appointment> resultVo = new ResultVo<Appointment>();
		resultVo.success(appointment);
		return resultVo;
	}

}

