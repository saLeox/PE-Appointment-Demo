package com.gof.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Provider8081_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(Provider8081_App.class, args);
	}
}
