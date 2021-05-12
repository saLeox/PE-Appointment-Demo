package com.gof.springcloud.vo;

import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.entity.Availability;
import com.gof.springcloud.entity.Confirmschedule;
import com.gof.springcloud.entity.Product;
import com.gof.springcloud.feign.vo.Client;
import com.gof.springcloud.feign.vo.Managerqualification;
import com.gof.springcloud.feign.vo.workflow.AuditDetailVo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value="Appointment detail")
public class AppointmentDetail {
	// internal data
	private Appointment appointment;
	private Product product;
	private Availability availability;
	private Confirmschedule confirmschedule;

	// external data
	private Client client;
	private Managerqualification managerqualification;
	private AuditDetailVo approvalDetail;
}
