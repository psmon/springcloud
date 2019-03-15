package com.webnori.psmon.cloudspring.akkacluster.actor;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import org.junit.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;


@SpringBootTest
public class ClusterClientTest {
    static private ActorSystem clusterSystem1;

    static private ActorSystem clusterSystem2;

    @BeforeClass
    public static void setup() throws IOException {
        //Load Config
        String serverConfig = new String( Files.readAllBytes( new ClassPathResource("server.conf").getFile().toPath()));
        String serverConfig2 = new String( Files.readAllBytes( new ClassPathResource("server2.conf").getFile().toPath()));

        clusterSystem1 = ActorSystem.create("ClusterSystem", ConfigFactory.parseString(serverConfig) );
        clusterSystem1.actorOf(Props.create(ClusterListener.class), "clusterListener");

        clusterSystem2 = ActorSystem.create("ClusterSystem", ConfigFactory.parseString(serverConfig2) );
        clusterSystem2.actorOf(Props.create(ClusterListener.class), "clusterListener");
    }

    @AfterClass
    public static void down() {
        clusterSystem2.terminate();
        clusterSystem1.terminate();

    }

    @Test
    public void test() throws InterruptedException {
        Thread.sleep(1000);

    }


}
