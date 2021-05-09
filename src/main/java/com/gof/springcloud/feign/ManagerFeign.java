package com.gof.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gof.springcloud.feign.vo.Managerqualification;

@FeignClient(name = "mock")
public interface ManagerFeign {
	@GetMapping("/manager")
	public Managerqualification getQualification(@RequestParam int key);
}
