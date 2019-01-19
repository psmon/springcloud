package com.webnori.psmon.cloudspring.datacenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DatacenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatacenterApplication.class, args);
	}

}

