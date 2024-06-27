package com.webcheckin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WebCheckinMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCheckinMicroserviceApplication.class, args);
	}

}
