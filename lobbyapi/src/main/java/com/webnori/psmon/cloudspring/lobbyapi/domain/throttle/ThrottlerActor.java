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

class IncBucket {
}

public class ThrottlerActor extends AbstractActor {
    private final Materializer materializer;
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private final ActorRef releaseThrottler;
    private int bucketSize;
    private int totalCnt;

    static public Props props(int bucketSize, int releaseBucketSppedSec, Materializer materializer) {
        return Props.create(ThrottlerActor.class, () -> new ThrottlerActor(bucketSize, releaseBucketSppedSec, materializer));
    }

    public ThrottlerActor(int bucketSize, int releaseBucketSppedSec, Materializer materializer) {
        this.materializer = materializer;
        this.bucketSize = bucketSize;
        totalCnt = 0;

        releaseThrottler = Source.actorRef(10000, OverflowStrategy.dropNew())
                .throttle(releaseBucketSppedSec, Duration.ofSeconds(1))
                .to(Sink.actorRef(getSelf(), NotUsed.getInstance()))
                .run(materializer);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(DesBucket.class, obj -> {
                    bucketSize--;
                    totalCnt++;
                    releaseThrottler.tell(new IncBucket(), getSelf());
                    log.info("DesBucket:{} Procssed:{} ", bucketSize, totalCnt);
                })
                .match(IncBucket.class, obj -> {
                    bucketSize++;
                    log.info("IncBucket:{} Procssed:{} ", bucketSize, totalCnt);
                })
                .match(AvableCall.class, obj -> {
                    Boolean bAvable = false;
                    if (0 < bucketSize)
                        bAvable = true;
                    getSender().tell(bAvable, null);
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
