package com.webnori.psmon.cloudspring.lobbyapi.actor;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.OverflowStrategy;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import akka.testkit.javadsl.TestKit;
import com.typesafe.config.ConfigFactory;
import com.webnori.psmon.cloudspring.library.akkatools.AkkaUtil;
import com.webnori.psmon.cloudspring.lobbyapi.config.AppConfiguration;
import com.webnori.psmon.cloudspring.lobbyapi.domain.throttle.PrintActor;
import com.webnori.psmon.cloudspring.lobbyapi.domain.throttle.ThrottlerActor;
import org.junit.AfterClass;
import org.junit.Assert;
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
public class PrintActorTest {
    static ActorSystem system;
    static Materializer materializer;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create("TestSystem", ConfigFactory.load().getConfig("test"));
        materializer = ActorMaterializer.create(system);
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testIt() throws Exception {
        ActorRef printer = system.actorOf(PrintActor.props(),"printer");
        ActorRef throttler = system.actorOf(ThrottlerActor.props(printer,materializer),"throttler");
        //int result = (int)AkkaUtil.AskToActor(throttler,10,5);
        //Assert.assertEquals(20,result);
    }

}
