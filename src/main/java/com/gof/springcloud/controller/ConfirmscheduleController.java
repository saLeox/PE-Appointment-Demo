package com.gof.springcloud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gof.springcloud.entity.Confirmschedule;
import com.gof.springcloud.service.ConfirmscheduleService;
import com.gof.springcloud.vo.ResultVo;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 用户确认计划 前端控制器
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@RestController
@RequestMapping("/confirmschedule")
public class ConfirmscheduleController {
	@Autowired
	private ConfirmscheduleService confirmscheduleService;
	@GetMapping
	@ApiOperation(value = "Get a confirmSchedule by key")
	public ResultVo<Confirmschedule> getConfirmSchedule(int key){
		ResultVo<Confirmschedule> resultVo = new ResultVo<Confirmschedule>();
		Confirmschedule confirmschedule = confirmscheduleService.getById(key);
		if (null == confirmschedule) resultVo.failure(404, "confirmSchedule not found");
		else resultVo.success(confirmschedule);
		return resultVo;
	}

	@PostMapping("/schedule")
	@ApiOperation(value = "Schedule a ConfirmCall")
	public ResultVo<String> scheduleConfirmCall(@RequestParam int auditId, @RequestBody int key) {
		return confirmscheduleService.schedule(auditId, key);
	}

	@PostMapping("/success")
	@ApiOperation(value = "CallBack Success")
	public ResultVo<String> callBackSuccess(@RequestParam int auditId, @RequestBody int key) {
		return confirmscheduleService.success(auditId, key);
	}

	@PostMapping("/failure")
	@ApiOperation(value = "CallBack Failure")
	public ResultVo<String> callBackFailure(@RequestParam int auditId, @RequestBody int key) {
		return confirmscheduleService.failure(auditId, key);
	}



}

