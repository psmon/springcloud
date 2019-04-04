package com.webnori.psmon.cloudspring.lobbyapi.domain.throttle;

import akka.NotUsed;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.stream.Materializer;
import akka.stream.OverflowStrategy;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;

import java.time.Duration;

class DecreseCnt { }

public class ThrottlerActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    ActorRef throttler;
    Materializer materializer;
    int countInSec=0;

    static public Props props(int secLimit, Materializer materializer) {
        return Props.create(ThrottlerActor.class, () -> new ThrottlerActor(secLimit,materializer));
    }

    public ThrottlerActor(int secLimit,Materializer materializer) {
        this.materializer = materializer;
        throttler = Source.actorRef(10000, OverflowStrategy.dropNew())
                .throttle(secLimit, Duration.ofSeconds(1))
                .to(Sink.actorRef(getSelf(), NotUsed.getInstance()))
                .run(materializer);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(IncreseCnt.class, obj ->{
                    countInSec++;
                    throttler.tell(new DecreseCnt(), getSelf());
                    log.info(String.format("SafeCall remain cnt %d",countInSec));
                })
                .match(DecreseCnt.class, obj -> {
                    countInSec--;
                })
                .match(CheckCnt.class, number ->{
                    getSender().tell(countInSec,null);
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
