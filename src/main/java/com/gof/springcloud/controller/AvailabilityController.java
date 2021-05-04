package com.gof.springcloud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gof.springcloud.entity.Availability;
import com.gof.springcloud.entity.ResultVo;
import com.gof.springcloud.service.AvailabilityService;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 私募产品剩余额度信息 前端控制器
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@RestController
@RequestMapping("/availability")
public class AvailabilityController {
	@Autowired
	private AvailabilityService availabilityService;
	@GetMapping
	@ApiOperation(value = "Get an availability by key")
	public ResultVo<Availability> getAvailability(int key){
		ResultVo<Availability> resultVo = new ResultVo<Availability>();
		Availability availability = availabilityService.getById(key);
		if (null == availability) resultVo.failure(404, "availability not found");
		else resultVo.success(availability);
		return resultVo;
	}

	@PostMapping
	@ApiOperation(value = "Create an availability")
	public ResultVo<Availability> createAvailability(Availability availability) {
		availability.setAvailabilityId(null);
		availabilityService.save(availability);
		ResultVo<Availability> resultVo = new ResultVo<Availability>();
		resultVo.success(availability);
		return resultVo;
	}

}

