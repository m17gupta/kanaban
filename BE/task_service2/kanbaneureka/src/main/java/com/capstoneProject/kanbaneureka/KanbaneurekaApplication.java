package com.capstoneProject.kanbaneureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class KanbaneurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanbaneurekaApplication.class, args);
	}

}
