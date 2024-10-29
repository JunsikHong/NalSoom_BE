package com.pick.nalsoom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NalsoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(NalsoomApplication.class, args);
	}

}
