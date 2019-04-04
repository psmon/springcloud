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

public class ThrottlerActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    ActorRef someActor;
    ActorRef throttler;
    Materializer materializer;

    static public Props props(ActorRef someActor, Materializer materializer) {
        return Props.create(ThrottlerActor.class, () -> new ThrottlerActor(someActor,materializer));
    }

    public ThrottlerActor(ActorRef someActor, Materializer materializer){
        this.someActor = someActor;
        this.materializer=materializer;
        throttler = Source.actorRef(10000, OverflowStrategy.dropNew())
                .throttle(1,  Duration.ofSeconds(1) )
                .to(Sink.actorRef(someActor, NotUsed.getInstance() ))
                .run(materializer);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Integer.class, number -> {
                    throttler.tell(number,getSelf());
                    log.info(String.format("===== Step 1 get message %d",number));
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
