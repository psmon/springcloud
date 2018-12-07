package com.webnori.psmon.cloudspring.frontweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
// Using a root package also allows the @ComponentScan annotation to be used without needing to specify a basePackage attribute
public class FrontWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontWebApplication.class, args);
	}
}
