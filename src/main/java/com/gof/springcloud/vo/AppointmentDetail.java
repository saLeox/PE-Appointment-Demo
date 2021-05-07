package com.gof.springcloud.vo;

import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.entity.Availability;
import com.gof.springcloud.feign.vo.Client;
import com.gof.springcloud.feign.vo.Managerqualification;

import lombok.Data;

@Data
public class AppointmentDetail {
	private Appointment appointment;
	private Availability availability;
	private Client client;
	private Managerqualification managerqualification;
}
