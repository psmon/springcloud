package com.webnori.psmon.cloudspring.akkacluster;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.webnori.psmon.cloudspring.akkacluster.actor.ClusterListener;
import com.webnori.psmon.cloudspring.akkacluster.config.SpringExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.webnori.psmon.cloudspring")
public class AkkaClusterApplication {

	public static void main(String[] args) {
		ApplicationContext context =SpringApplication.run(AkkaClusterApplication.class, args);

		ActorSystem system = context.getBean(ActorSystem.class);

		// Create an actor that handles cluster domain events
		system.actorOf(Props.create(ClusterListener.class), "clusterListener");

	}
}
