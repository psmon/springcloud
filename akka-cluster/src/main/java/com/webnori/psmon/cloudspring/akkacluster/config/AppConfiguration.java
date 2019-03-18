package com.webnori.psmon.cloudspring.akkacluster.config;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Lazy
@Configuration
@ComponentScan
public class AppConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${akka.hostname}")
    private String hostname;

    @Value("${akka.role}")
    private String role;

    @Value("${akka.port}")
    private String port;

    @Value("${akka.seed-nodes}")
    private String seedNodes;

    @Bean
    public ActorSystem actorSystem() {

        String minConfig = String.format("akka.remote.netty.tcp.hostname = \"%s\" \n " +
                "akka.remote.netty.tcp.port = %s \n" +
                "akka.cluster.roles = [%s] \n " +
                "akka.cluster.seed-nodes = %s \n ", hostname,port,role,seedNodes);

        final Config config = ConfigFactory.parseString(
                minConfig).withFallback(
                ConfigFactory.load("application"));

        ActorSystem system = ActorSystem.create("ClusterSystem",config);
        SpringExtension.SPRING_EXTENSION_PROVIDER.get(system)
                .initialize(applicationContext);
        return system;
    }
}
