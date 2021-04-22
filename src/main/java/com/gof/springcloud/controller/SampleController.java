package com.gof.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gof.springcloud.entity.ResultVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("sample control")
public class SampleController {
	private Logger log = LoggerFactory.getLogger(SampleController.class);

	@PostMapping("/postSample")
	@ApiOperation(value = "Add a travelPlan", notes = "Add a travelPlan")
	public ResultVo<String> postSample(){
		return new ResultVo<String>(true);
	}

	@GetMapping
	@ApiOperation(value = "Get sample")
	public ResultVo<String> getSample(){
		return new ResultVo<String>("test", true);
	}


}
