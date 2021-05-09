package com.gof.springcloud.rabbit;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.entity.Confirmschedule;
import com.gof.springcloud.entity.Product;
import com.gof.springcloud.feign.MockFeign;
import com.gof.springcloud.feign.vo.Calltask;
import com.gof.springcloud.service.AppointmentService;
import com.gof.springcloud.service.ConfirmscheduleService;
import com.gof.springcloud.service.ProductService;

@EnableBinding(DelayedSink.class)
@Component
public class DelayedReceiver {

	@Autowired
	private ConfirmscheduleService confirmscheduleService;
	@Autowired
	private ProductService productService;
	@Autowired
	private MockFeign calltaskFeign;
	@Autowired
	private AppointmentService appointmentService;

	@StreamListener(DelayedSink.INPUT)
	public void receive(String message) {
		System.out.println("Received time: " + LocalDateTime.now() + "  Received: " + message);
		Confirmschedule schedule = confirmscheduleService.getById(Integer.valueOf(message));

		// request the callcentre
		Appointment appointment = appointmentService.getById(schedule.getAppointmentId());
		Product product = productService.getById(appointment.getAppointmentId());
		Calltask calltask = Calltask.builder()
				.appointmentId(appointment.getAppointmentId())
				.clientId(appointment.getClientId())
				.managerId(appointment.getManagerId())
				.productCode(product.getProdoctCode())
				.build();
		calltaskFeign.createCalltask(calltask);

		// change the status
		schedule.setCalloutTime(new Date());
		confirmscheduleService.updateById(schedule);
	}

}
