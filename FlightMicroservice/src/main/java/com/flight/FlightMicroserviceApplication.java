package com.flight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FlightMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightMicroserviceApplication.class, args);
	}

}
