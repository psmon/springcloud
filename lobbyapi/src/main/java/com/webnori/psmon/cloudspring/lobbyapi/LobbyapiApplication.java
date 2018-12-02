package com.webnori.psmon.cloudspring.lobbyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LobbyapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LobbyapiApplication.class, args);
	}
}
