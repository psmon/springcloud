package com.webnori.psmon.cloudspring.accountapi.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.webnori.psmon.cloudspring.library.common.message.CMD_REMOTE;
import com.webnori.psmon.cloudspring.library.common.message.Greet;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GreetingActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Greet.class, s -> {
                    log.info("Received String message: {}", s);
                })
                .match(CMD_REMOTE.class, c->{
                    log.info("Received Remote message: {}", "CMD_REMOTE");
                    if(c.getMessageType()==5){
                        // Test...
                        CMD_REMOTE cmd_res = new CMD_REMOTE(6,"res");
                        getSender().tell(cmd_res, ActorRef.noSender());
                    }
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

}
