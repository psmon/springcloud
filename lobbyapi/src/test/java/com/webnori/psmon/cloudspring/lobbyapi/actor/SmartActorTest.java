package com.webnori.psmon.cloudspring.lobbyapi.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import com.typesafe.config.ConfigFactory;
import com.webnori.psmon.cloudspring.library.common.message.CMD_REMOTE;
import com.webnori.psmon.cloudspring.lobbyapi.config.AppConfiguration;
import com.webnori.psmon.cloudspring.lobbyapi.domain.dispatchers.SmartActor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.Duration;

@SpringBootTest
@ContextConfiguration(classes = AppConfiguration.class)
@SuppressWarnings("Duplicates")
@ActiveProfiles("test")
public class SmartActorTest {
    static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create("TestSystem", ConfigFactory.load().getConfig("test"));
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testIt() throws InterruptedException {
        new TestKit(system) {{
            ActorRef probe = getRef();
            ActorRef fastActor1 = system.actorOf(SmartActor.props("i'am fast").withDispatcher("fast-dispatcher"), "fast1");
            ActorRef fastActor2 = system.actorOf(SmartActor.props("i'am fast").withDispatcher("fast-dispatcher"), "fast2");
            ActorRef fastActor3 = system.actorOf(SmartActor.props("i'am fast").withDispatcher("fast-dispatcher"), "fast3");

            ActorRef slowActor1 = system.actorOf(SmartActor.props("i'am slow").withDispatcher("slow-dispatcher"), "slow1");
            ActorRef slowActor2 = system.actorOf(SmartActor.props("i'am slow").withDispatcher("slow-dispatcher"), "slow2");
            ActorRef slowActor3 = system.actorOf(SmartActor.props("i'am slow").withDispatcher("slow-dispatcher"), "slow3");

            for (int i = 0; i < 1000; i++) {
                fastActor1.tell(i, ActorRef.noSender());
                fastActor2.tell(i, ActorRef.noSender());
                fastActor3.tell(i, ActorRef.noSender());

                slowActor1.tell(i, ActorRef.noSender());
                slowActor2.tell(i, ActorRef.noSender());
                slowActor3.tell(i, ActorRef.noSender());
            }
            Thread.sleep(10000);
        }};
    }
}
