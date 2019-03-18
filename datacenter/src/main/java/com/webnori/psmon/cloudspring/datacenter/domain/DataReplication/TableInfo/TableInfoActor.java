package com.webnori.psmon.cloudspring.datacenter.domain.DataReplication.TableInfo;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.webnori.psmon.cloudspring.library.common.message.TableInfo.TableCMD;
import com.webnori.psmon.cloudspring.library.common.message.TableInfo.TableInfo;
import com.webnori.psmon.cloudspring.library.common.message.TableInfo.TableInfoList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableInfoActor extends AbstractActor {

    TableRepository tableRepository;

    List<TableInfo> tableInfos;

    int seqNumSync;

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    // Props == Object creation hint
    static public Props props(TableRepository tableRepository) {
        return Props.create(TableInfoActor.class, () -> new TableInfoActor(tableRepository));
    }

    public TableInfoActor(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
        tableInfos = new ArrayList<>();
        seqNumSync = 0;
    }

    @Override
    public void preStart() {
        // first read for sync , After that no more db load
        tableRepository.findAllTableInfos().forEach(tableEntity -> {
            TableInfo tableInfo = tableEntity.toTableInfo();
            tableInfos.add(tableInfo);
        });
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(TableCMD.class, s -> {
                    if(s.cmdType==TableCMD.TableCMDType.SYNC_FIRST){
                        log.info("Received TableCMD message: {}", s);
                        ActorRef requestActor = getSender();
                        seqNumSync++;
                        requestActor.tell(new TableInfoList(seqNumSync,tableInfos),ActorRef.noSender());
                    }
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

}
