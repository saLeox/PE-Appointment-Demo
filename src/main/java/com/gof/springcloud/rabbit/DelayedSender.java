package com.gof.springcloud.rabbit;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class DelayedSender {

	@Autowired
	private DelayedSink delayedSink;

	/**
	 * @param msg 消息
	 * @param time 间隔时间 (ms)
	 */
	public void delayedMessage(String msg, int time) {
		System.out.println("Send time: " + LocalDateTime.now() + "  Send: " + msg);
		// 延时时间20秒
		delayedSink.output().send(MessageBuilder.withPayload(msg).setHeader("x-delay", time).build());
	}

}
