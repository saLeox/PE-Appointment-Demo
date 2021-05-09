package com.gof.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;

import com.gof.springcloud.feign.vo.Calltask;
import com.gof.springcloud.vo.ResultVo;

@FeignClient(name = "mock")
public interface CalltaskFeign {

	@PostMapping("/calltask")
	public ResultVo<Calltask> createCalltask(@SpringQueryMap Calltask calltask);
}
