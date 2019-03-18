package com.webnori.psmon.cloudspring.lobbyapi.domain.tableinfo;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.ReceiveTimeout;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.FromConfig;
import com.webnori.psmon.cloudspring.library.common.message.TableInfo.TableCMD;
import com.webnori.psmon.cloudspring.library.common.message.TableInfo.TableInfo;
import com.webnori.psmon.cloudspring.library.common.message.TableInfo.TableInfoList;
import scala.concurrent.duration.Duration;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TableInfoActor extends AbstractActor {

    List<TableInfo> tableInfos;

    int seqNumSync;

    ActorRef dataCenter = getContext().actorOf(FromConfig.getInstance().props(),
            "dcTableSyncRouter");

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    // Props == Object creation hint
    static public Props props() {
        return Props.create(TableInfoActor.class, () -> new TableInfoActor());
    }

    public TableInfoActor() {
        tableInfos = Collections.emptyList();
        seqNumSync = 0;
    }

    @Override
    public void preStart() {
        // first requestData
        sendRefreshTable(TableCMD.TableCMDType.SYNC_FIRST);
        getContext().setReceiveTimeout(Duration.create(10, TimeUnit.SECONDS));
    }

    private void sendRefreshTable(TableCMD.TableCMDType cmdType) {
        dataCenter.tell(new TableCMD(cmdType),getSelf() );
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(TableInfoList.class, tableList -> {
                    tableInfos = tableList.getValues();
                    log.info(String.format("===== first Table sync, count:%d",tableInfos.size()));
                })
                .match(ReceiveTimeout.class, message -> {
                    log.info("=== Timer");
                    sendRefreshTable(TableCMD.TableCMDType.SYNC_FIRST);
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
