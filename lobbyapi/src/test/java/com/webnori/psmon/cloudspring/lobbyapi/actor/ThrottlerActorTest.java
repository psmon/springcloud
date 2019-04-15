package com.webnori.psmon.cloudspring.lobbyapi.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.testkit.javadsl.TestKit;
import com.typesafe.config.ConfigFactory;
import com.webnori.psmon.cloudspring.library.akkatools.AkkaUtil;
import com.webnori.psmon.cloudspring.lobbyapi.config.AppConfiguration;
import com.webnori.psmon.cloudspring.lobbyapi.domain.throttle.CheckCnt;
import com.webnori.psmon.cloudspring.lobbyapi.domain.throttle.IncreseCnt;
import com.webnori.psmon.cloudspring.lobbyapi.domain.throttle.ThrottlerActor;
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

    private void waitForUnderRemainCnt(ActorRef throttler,int cntPerSec) throws Exception {
        while (true) {
            int remainCnt = (int) AkkaUtil.AskToActor(throttler, new CheckCnt(), 5);
            if (remainCnt < cntPerSec) {
                break;
            }
            Thread.sleep(500);
        }
    }

    private int wairForAllCompleted(ActorRef throttler) throws Exception {
        int remainCnt = -1;
        while (true) {
            remainCnt = (int) AkkaUtil.AskToActor(throttler, new CheckCnt(), 5);
            if (remainCnt == 0) {
                break;
            }
            Thread.sleep(500);
        }
        return remainCnt;
    }

    @Test
    public void testIt() throws Exception {
        // given
        int cntPerSec = 10; //유입량과 상관없이 초당 10번이 처리되도록 밸브셋팅
        ActorRef throttler = system.actorOf(ThrottlerActor.props(cntPerSec, materializer), "throttler");

        // 동기적 API 100번 호출 시나리오
        for (int i = 0; i < 100; i++) {
            waitForUnderRemainCnt(throttler,cntPerSec);
            throttler.tell(new IncreseCnt(), null);
            //동기처리는 이곳에...
        }

        // then : 모두 처리가되는지 검증
        int remainCnt = wairForAllCompleted(throttler);
        Assert.assertEquals(0,remainCnt);
    }
}
