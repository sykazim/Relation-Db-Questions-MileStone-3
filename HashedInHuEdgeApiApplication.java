package com.netflix.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HashedInHuEdgeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HashedInHuEdgeApiApplication.class, args);
	}

}
