package com.gof.springcloud.service.validator.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gof.springcloud.constants.Constants;
import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.service.AppointmentService;
import com.gof.springcloud.service.validator.Validator;
import com.gof.springcloud.vo.ResultVo;

@Component
public class AppointmentValidator<Q> implements Validator<Q>{

	@Autowired
	private AppointmentService appointmentService;

	@Override
	public ResultVo validate(Object t) {
		ResultVo<String> resultVo = new ResultVo<String>();
		Appointment appointment = (Appointment) t;
		if (appointment.getClientId() == null) {
			resultVo.failure(404, "Client not found");
			return resultVo;
		}
		if (appointment.getProductId() == null) {
			resultVo.failure(404, "Product not found");
			return resultVo;
		}
		int cnt = appointmentService.count(new QueryWrapper<Appointment>()
				.eq("product_id", appointment.getProductId())
				.and(wrapper -> wrapper.eq("client_id", appointment.getClientId()))
				.and(wrapper -> wrapper
						.eq("status", Constants.APPOINTMENT_STATUS_PENDING)
							.or().eq("status", Constants.APPOINTMENT_STATUS_SUCCESS)));
		if (cnt > 0) {
			resultVo.failure(404, "Not allow second appointment");
			return resultVo;
		}
		resultVo.success(null);
		return resultVo;
	}

}
