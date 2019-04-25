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
import java.time.Instant;

class DecreseCnt {
}

public class ThrottlerActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private ActorRef throttler;
    private ActorRef slowThrottler;
    Materializer materializer;
    private int countInSec = 0;
    private int secLimit;
    private Boolean slowMode = false;
    private Instant lastCall;
    private int releseBucketSec = 3;

    static public Props props(int secLimit, Materializer materializer) {
        return Props.create(ThrottlerActor.class, () -> new ThrottlerActor(secLimit, materializer));
    }

    public ThrottlerActor(int secLimit, Materializer materializer) {
        this.materializer = materializer;
        this.secLimit = secLimit;
        throttler = Source.actorRef(10000, OverflowStrategy.dropNew())
                .throttle(secLimit, Duration.ofSeconds(1))
                .to(Sink.actorRef(getSelf(), NotUsed.getInstance()))
                .run(materializer);
        slowThrottler = Source.actorRef(10000, OverflowStrategy.dropNew())
                .throttle(1, Duration.ofSeconds(1))
                .to(Sink.actorRef(getSelf(), NotUsed.getInstance()))
                .run(materializer);
    }

    private void switchSpeed(){
        if(lastCall!=null){
            if(slowMode){
                Duration timeElapsed = Duration.between(lastCall, Instant.now());
                if(timeElapsed.getSeconds() > secLimit*releseBucketSec){
                    slowMode=false;
                }
            }else{
                if (countInSec >= secLimit) {
                    slowMode = true;
                }
            }
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(IncreseCnt.class, obj -> {
                    countInSec++;
                    switchSpeed();

                    ActorRef runActor = null;

                    if (slowMode)
                        runActor = slowThrottler;
                    else
                        runActor = throttler;

                    lastCall = Instant.now();
                    runActor.tell(new DecreseCnt(), getSelf());
                    log.info("SafeCall-SlowMode:{} remain cnt {}",slowMode ,countInSec);
                })
                .match(DecreseCnt.class, obj -> {
                    countInSec--;
                })
                .match(AvableCall.class, obj -> {
                    Boolean bAvable = false;
                    if (slowMode) {
                        if (countInSec == 0)
                            bAvable = true;
                    } else {
                        if (countInSec < secLimit)
                            bAvable = true;
                    }
                    getSender().tell(bAvable, null);
                })
                .match(CheckCnt.class, number -> {
                    getSender().tell(countInSec, null);
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
