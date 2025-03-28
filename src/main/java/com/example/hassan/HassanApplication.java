package com.example.hassan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HassanApplication {

	public static void main(String[] args) {
		SpringApplication.run(HassanApplication.class, args);
	}

}
