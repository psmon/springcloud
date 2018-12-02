package com.webnori.psmon.cloudspring.eurekaserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceregistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceregistryApplication.class, args);
	}
}
