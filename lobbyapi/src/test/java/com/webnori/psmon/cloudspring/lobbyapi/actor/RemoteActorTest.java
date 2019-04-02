package com.webnori.psmon.cloudspring.lobbyapi.actor;


import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;

import com.typesafe.config.ConfigFactory;
import com.webnori.psmon.cloudspring.library.common.message.CMD_REMOTE;
import com.webnori.psmon.cloudspring.lobbyapi.config.AppConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;

@SpringBootTest
@ContextConfiguration(classes = AppConfiguration.class)
@SuppressWarnings("Duplicates")
@ActiveProfiles("localtest")
public class RemoteActorTest {

    static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create("TestSystem",ConfigFactory.load().getConfig("test"));
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testIt() {
        new TestKit(system) {{
            ActorRef probe = getRef();
            ActorSelection accountGreeter =
                    system.actorSelection("akka.tcp://accountapi@0.0.0.0:2552/user/greeter");

            accountGreeter.tell(new CMD_REMOTE(5,"hi"),getRef());
            String expectMessage = expectMsgClass(Duration.ofSeconds(5),CMD_REMOTE.class).getMessage();
        }};
    }

}
