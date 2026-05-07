package com.servicemate.servicemate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServicemateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicemateApplication.class, args);
	}

}
