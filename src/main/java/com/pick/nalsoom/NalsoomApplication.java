package com.pick.nalsoom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication
public class NalsoomApplication {
	//v1
	public static void main(String[] args) {
		SpringApplication.run(NalsoomApplication.class, args);
	}

}
