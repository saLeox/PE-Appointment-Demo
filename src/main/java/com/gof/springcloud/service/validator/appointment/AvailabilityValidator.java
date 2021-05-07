package com.gof.springcloud.service.validator.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.entity.Availability;
import com.gof.springcloud.entity.Product;
import com.gof.springcloud.service.AvailabilityService;
import com.gof.springcloud.service.ProductService;
import com.gof.springcloud.service.validator.Validator;
import com.gof.springcloud.vo.ResultVo;

@Component
public class AvailabilityValidator<Q> implements Validator<Q>{

	@Autowired
	private AvailabilityService availabilityService;
	@Autowired
	private ProductService productService;

	@Override
	public ResultVo validate(Object t) {
		ResultVo<String> resultVo = new ResultVo<String>();
		Appointment appointment = (Appointment) t;
		if (appointment.getProductId() == null) {
			resultVo.failure(404, "Product not found");
			return resultVo;
		}
		Product product = productService.getById(appointment.getProductId());
		if (product == null) {
			resultVo.failure(404, "Product not found");
			return resultVo;
		}
		if (appointment.getSubscribeQty().compareTo(product.getFundCapacity()) > 0) {
			resultVo.failure(404, "Over Fund Capacity");
			return resultVo;
		}
		Availability availability = availabilityService
				.getOne(new QueryWrapper<Availability>().eq("product_id", appointment.getProductId()));

		if (availability == null) {
			resultVo.success(null);
			return resultVo;
		}
		if (availability.getInvestorAvailability().intValue() <= 0) {
			resultVo.failure(404, "No more Investor Availability");
			return resultVo;
		}
		if (appointment.getSubscribeQty().compareTo(availability.getFundAvailability()) > 0) {
			resultVo.failure(404, "Over Fund Availability");
			return resultVo;
		}
		resultVo.success(null);
		return resultVo;
	}

}
