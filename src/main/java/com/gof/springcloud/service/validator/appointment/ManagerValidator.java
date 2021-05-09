package com.gof.springcloud.service.validator.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.feign.MockFeign;
import com.gof.springcloud.feign.vo.Managerqualification;
import com.gof.springcloud.service.validator.Validator;
import com.gof.springcloud.vo.ResultVo;

@Component
public class ManagerValidator<Q> implements Validator<Q>{

	@Autowired
	private MockFeign managerFeign;

	@Override
	public ResultVo validate(Object t) {
		ResultVo<String> resultVo = new ResultVo<String>();
		Appointment appointment = (Appointment) t;
		if (appointment.getManagerId() == null) {
			resultVo.failure(404, "Manager not found");
			return resultVo;
		}
		Managerqualification qualification = managerFeign.getQualification(appointment.getManagerId());
		if (qualification.getHaspri() == null || qualification.getHaspri().intValue() != 1) {
			resultVo.failure(404, "Manager don't qualify");
		}
		resultVo.success(null);
		return resultVo;
	}

}
