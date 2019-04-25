package com.webnori.psmon.cloudspring.lobbyapi.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.testkit.javadsl.TestKit;
import com.typesafe.config.ConfigFactory;
import com.webnori.psmon.cloudspring.library.akkatools.AkkaUtil;
import com.webnori.psmon.cloudspring.lobbyapi.config.AppConfiguration;
import com.webnori.psmon.cloudspring.lobbyapi.domain.throttle.AvableCall;
import com.webnori.psmon.cloudspring.lobbyapi.domain.throttle.CheckCnt;
import com.webnori.psmon.cloudspring.lobbyapi.domain.throttle.IncreseCnt;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
@ContextConfiguration(classes = AppConfiguration.class)
@SuppressWarnings("Duplicates")
@ActiveProfiles("test")
public class ThrottlerActorTest {
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

    private void waifForAvable(ActorRef throttler) throws Exception {
        while (true) {
            Boolean avableCall = (Boolean) AkkaUtil.AskToActor(throttler, new AvableCall(), 5);
            if (avableCall) {
                break;
            }
            Thread.sleep(500);
        }
    }

    @Test
    public void testIt() throws Exception {
        // given
        int cntPerSec = 10;
        ActorRef throttler = system.actorOf(com.webnori.psmon.cloudspring.lobbyapi.domain.throttle.ThrottlerActor.props(cntPerSec, materializer), "throttler");

        // when
        for (int i = 0; i < 13; i++) {
            waifForAvable(throttler);
            throttler.tell(new IncreseCnt(), null);
        }

        Thread.sleep(1000*9);

        for (int i = 0; i < 10; i++) {
            waifForAvable(throttler);
            throttler.tell(new IncreseCnt(), null);
        }

        // then
        int remainCnt = -1;
        while (true) {
            remainCnt = (int) AkkaUtil.AskToActor(throttler, new CheckCnt(), 5);
            if (remainCnt == 0) {
                break;
            }
            Thread.sleep(500);
        }
        Assert.assertEquals(0,remainCnt);

    }
}
