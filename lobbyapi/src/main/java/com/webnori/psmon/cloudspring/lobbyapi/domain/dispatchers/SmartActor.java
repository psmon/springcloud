package com.webnori.psmon.cloudspring.lobbyapi.domain.dispatchers;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class SmartActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private String name;

    static public Props props(String name) {
        return Props.create(SmartActor.class, () -> new SmartActor(name));
    }

    public SmartActor(String name) {
        this.name = name;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Integer.class, tableList -> {
                    Thread.sleep(500);  // delay for test
                    log.info(String.format("===== process msg:%s", name));
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

}
