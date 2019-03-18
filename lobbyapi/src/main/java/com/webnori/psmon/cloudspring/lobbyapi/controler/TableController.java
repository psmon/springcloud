package com.webnori.psmon.cloudspring.lobbyapi.controler;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import com.webnori.psmon.cloudspring.library.akkatools.AkkaUtil;
import com.webnori.psmon.cloudspring.library.common.message.TableInfo.TableCMD;
import com.webnori.psmon.cloudspring.library.common.message.TableInfo.TableInfo;
import com.webnori.psmon.cloudspring.lobbyapi.restclient.AccountClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping(path="/table")
public class TableController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableController.class);

    @Autowired
    private ActorSystem system;


    @RequestMapping("/list")
    List<TableInfo> getList() {
        try{
            String actorPath = "user/lobbyTableSync";
            ActorSelection lobbayTableActor = system.actorSelection(actorPath);
            return (List<TableInfo>) AkkaUtil.AskToActorSelect(lobbayTableActor,new TableCMD(TableCMD.TableCMDType.SYNC_FIRST),1);
        }catch (Exception e){
            LOGGER.error(e.toString());
            return null;
        }
    }
}
