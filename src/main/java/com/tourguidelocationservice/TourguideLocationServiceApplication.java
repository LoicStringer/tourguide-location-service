package com.tourguidelocationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.tourguidelocationservice")
public class TourguideLocationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourguideLocationServiceApplication.class, args);
	}

}
