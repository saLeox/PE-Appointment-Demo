package com.gof.springcloud.service.validator.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.feign.ClientFeign;
import com.gof.springcloud.feign.vo.Client;
import com.gof.springcloud.service.validator.Validator;
import com.gof.springcloud.vo.ResultVo;

@Component
public class ClientValidator<Q> implements Validator<Q>{

	@Autowired
	private ClientFeign clientFeign;

	@Override
	public ResultVo validate(Object t) {
		ResultVo<String> resultVo = new ResultVo<String>();
		Appointment appointment = (Appointment) t;
		if (appointment.getClientId() == null) {
			resultVo.failure(404, "Client not found");
			return resultVo;
		}
		Client client = clientFeign.getClient(appointment.getClientId());
		if (client.getAge().intValue() > 60) {
			resultVo.failure(404, "Client overage");
			return resultVo;
		}
		if (client.getRisklevel().intValue() <= 3) {
			resultVo.failure(404, "Risk level don't reach standard");
			return resultVo;
		}
		if (client.getProftype().intValue() != 1) {
			resultVo.failure(404, "Not professional investor");
			return resultVo;
		}
		resultVo.success(null);
		return resultVo;
	}

}
