package com.gof.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gof.springcloud.feign.vo.Client;

@FeignClient(name = "ClientFeign",url = "${feign.url.client}")
public interface ClientFeign {

	@GetMapping
	public Client getClient(@RequestParam int key);
}
