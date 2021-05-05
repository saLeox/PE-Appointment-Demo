package com.gof.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gof.springcloud.rabbit.DelayedSender;

@RestController
public class MsgController {

	@Autowired
	private DelayedSender delayedSender;

	/**
	 * 测试发送延时消息
	 *
	 * @return
	 */
	@GetMapping("/scs/delayedSender")
	public String delayedSender(String msg, int time) {
		delayedSender.delayedMessage(msg, time);
		return "delay msg ok";
	}

}
