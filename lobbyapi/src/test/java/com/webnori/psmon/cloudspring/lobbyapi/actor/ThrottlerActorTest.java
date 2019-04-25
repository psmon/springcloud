package com.webnori.psmon.cloudspring.lobbyapi.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.testkit.javadsl.TestKit;
import com.typesafe.config.ConfigFactory;
import com.webnori.psmon.cloudspring.library.akkatools.AkkaUtil;
import com.webnori.psmon.cloudspring.lobbyapi.config.AppConfiguration;
import com.webnori.psmon.cloudspring.lobbyapi.domain.throttle.AvableCall;
import com.webnori.psmon.cloudspring.lobbyapi.domain.throttle.DesBucket;
import com.webnori.psmon.cloudspring.lobbyapi.domain.throttle.ThrottlerActor;
import org.junit.AfterClass;
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
    static private LoggingAdapter log;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create("TestSystem", ConfigFactory.load().getConfig("test"));
        log = Logging.getLogger(system, "ThrottlerActorTest");
        materializer = ActorMaterializer.create(system);
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    private void waitForUnderRemainCnt(ActorRef throttler) throws Exception {
        while (true) {
            boolean isEnoughBucket = (boolean) AkkaUtil.AskToActor(throttler, new AvableCall(), 5);
            if (isEnoughBucket) {
                break;
            }
            Thread.sleep(500);
        }
    }

    @Test
    public void testIt() throws Exception {
        // given
        int bucketSize = 10; //최초 버킷...
        int releaseBucketSppedSec = 1;
        int totalCnt = 0;

        ActorRef throttler = system.actorOf(ThrottlerActor.props(bucketSize,releaseBucketSppedSec, materializer), "throttler");

        // 동기적 API 20 호출 시나리오
        for (int i = 0; i < 20; i++) {
            waitForUnderRemainCnt(throttler);
            throttler.tell(new DesBucket(), null);
            //동기처리는 이곳에...
            totalCnt++;
            log.info("========= continue job{} ==========",totalCnt);
        }

        //10초간 휴식
        log.info("====== take rest for 10sec");

        Thread.sleep(10000);

        log.info("====== resume task....");

        //10개 추가처리
        for (int i = 0; i < 10; i++) {
            waitForUnderRemainCnt(throttler);
            throttler.tell(new DesBucket(), null);
            //동기처리는 이곳에...
            totalCnt++;
            log.info("========= continue job{} ==========",totalCnt);
        }

    }
}
