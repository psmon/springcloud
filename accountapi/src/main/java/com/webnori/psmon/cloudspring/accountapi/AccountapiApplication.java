package com.webnori.psmon.cloudspring.accountapi;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import com.webnori.psmon.cloudspring.accountapi.config.SpringExtension;
import com.webnori.psmon.cloudspring.library.akkatools.SimpleClusterListner;
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

		// Create an actor that handles cluster domain events
		system.actorOf(Props.create(SimpleClusterListner.class), "clusterListener");

		// Create Actor in APP
		ActorRef greeter = system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system)
				.props("greetingActor"), "greeter");

		greeter.tell(new Greet("Spring Boot Start -AccountAPI"),ActorRef.noSender());

		// How to send a message to an actor from another remote application
		//ActorSelection remoteGreeter = system.actorSelection("akka.tcp://akka-accountapi@127.0.0.1:9999/user/greeter");

	}
}
