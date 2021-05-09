package com.gof.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gof.springcloud.feign.vo.Calltask;
import com.gof.springcloud.feign.vo.Client;
import com.gof.springcloud.feign.vo.Managerqualification;
import com.gof.springcloud.vo.ResultVo;

@FeignClient(name = "mock")
public interface MockFeign {

	@GetMapping("/client")
	public Client getClient(@RequestParam int key);

	@GetMapping("/manager")
	public Managerqualification getQualification(@RequestParam int key);

	@PostMapping("/calltask")
	public ResultVo<Calltask> createCalltask(@SpringQueryMap Calltask calltask);

}
