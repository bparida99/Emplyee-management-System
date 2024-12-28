package com.ai.aibotservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AiBotServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiBotServiceApplication.class, args);
	}

}
