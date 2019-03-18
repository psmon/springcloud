package com.webnori.psmon.cloudspring.lobbyapi;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.Cluster;
import com.webnori.psmon.cloudspring.library.akkatools.SimpleClusterListner;
import com.webnori.psmon.cloudspring.library.service.MyService;
import com.webnori.psmon.cloudspring.lobbyapi.domain.tableinfo.TableInfoActor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.webnori.psmon.cloudspring")
@EnableSwagger2
public class LobbyapiApplication {

	public static void main(String[] args) {

		ApplicationContext context =SpringApplication.run(LobbyapiApplication.class, args);

		ActorSystem system = context.getBean(ActorSystem.class);

		// Create an actor that handles cluster domain events
		system.actorOf(Props.create(SimpleClusterListner.class), "clusterListener");

		//Create a TableSync object when it joins the cluster
		Cluster.get(system).registerOnMemberUp(new Runnable() {
			@Override
			public void run() {
				system.actorOf(TableInfoActor.props(),"tableSync");
			}
		});
	}
}
