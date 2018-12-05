package com.webnori.psmon.cloudspring.eurekaserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaServer
@SpringBootApplication
@EnableSwagger2
public class EurekaServiceregistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceregistryApplication.class, args);
	}
}
