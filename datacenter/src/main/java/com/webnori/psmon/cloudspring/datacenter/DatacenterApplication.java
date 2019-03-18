package com.webnori.psmon.cloudspring.datacenter;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.webnori.psmon.cloudspring.datacenter.domain.DataReplication.TableInfo.TableInfoActor;
import com.webnori.psmon.cloudspring.datacenter.domain.DataReplication.TableInfo.TableMemoryRepository;
import com.webnori.psmon.cloudspring.library.akkatools.SimpleClusterListner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.webnori.psmon.cloudspring")
@EnableSwagger2
public class DatacenterApplication {

	public static void main(String[] args) {

		ApplicationContext context =SpringApplication.run(DatacenterApplication.class, args);

		ActorSystem system = context.getBean(ActorSystem.class);

		// Create an actor that handles cluster domain events
		system.actorOf(Props.create(SimpleClusterListner.class), "clusterListener");

		TableMemoryRepository tableMemoryRepository = new TableMemoryRepository();
		tableMemoryRepository.createDummyData();
		ActorRef tableInfoActor = system.actorOf(TableInfoActor.props(tableMemoryRepository),"dcTableSyncRouter");

	}

}

