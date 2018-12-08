package com.webnori.psmon.cloudspring.accountapi;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import com.webnori.psmon.cloudspring.accountapi.config.SpringExtension;
import com.webnori.psmon.cloudspring.library.common.message.Greet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;


import scala.concurrent.duration.FiniteDuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.TimeUnit;


@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.webnori.psmon.cloudspring")
@EnableSwagger2
public class AccountapiApplication {

	public static void main(String[] args) {

		ApplicationContext context =SpringApplication.run(AccountapiApplication.class, args);

		ActorSystem system = context.getBean(ActorSystem.class);

		ActorRef greeter = system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system)
				.props("greetingActor"), "greeter");

		greeter.tell(new Greet("Spring Boot Start -AccountAPI"),ActorRef.noSender());

	}
}
