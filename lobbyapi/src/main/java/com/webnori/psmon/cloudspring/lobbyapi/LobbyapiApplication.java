package com.webnori.psmon.cloudspring.lobbyapi;

import com.webnori.psmon.cloudspring.library.service.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.webnori.psmon.cloudspring")
@EnableSwagger2
public class LobbyapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LobbyapiApplication.class, args);
	}
}
