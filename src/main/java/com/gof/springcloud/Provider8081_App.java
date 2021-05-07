package com.gof.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.gof.springcloud.feign"})
@EnableTransactionManagement //open the transaction management
public class Provider8081_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(Provider8081_App.class, args);
	}
}
