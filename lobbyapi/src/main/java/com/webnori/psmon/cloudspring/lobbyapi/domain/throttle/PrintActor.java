package com.webnori.psmon.cloudspring.lobbyapi.domain.throttle;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PrintActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    static public Props props() {
        return Props.create(PrintActor.class, () -> new PrintActor());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Integer.class, number -> {
                    Thread.sleep(1000);  // delay for test
                    Integer result = number * 2;
                    getSender().tell(result,null);
                    log.info(String.format("===== Step 2 completed %d",result));
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
