# Spring Cloud for Distributed applications

##Goal
Distributed Micro Game Services Using Spring Cloud

![Alt text](library/doc-res/spring-cloud-arc.png)


# Centeralized Config
* http://git.webnori.com/projects/SB2/repos/cloudconfig/browse

    If you want to change the path, modify application.peoperties in config-service

## Local Run
* FullBuild: root>mvn clean install
* AppRun: root/{APPPATH}> mvn spring-boot:run -Dspring.profiles.active="local"
* Recommend : Using Spring Dashboard

## Docker Run - Local
* BuildAndRun : {APPPATH}> dockbuild.cmd

## Kubernetes
* kubernetes/{APPNAME}.yaml

## Service Concept
* [ConfigService](config-service/readme.MD) : Centerlized config - [Sample](config-sample/)
* [DiscoveryService](eureka-serviceregistry/readme.MD) : Con
* [AccountAPI](accountapi/readme.MD) : DB(JPA) / Rest
* [LobbyAPI](lobbyapi/readme.MD) : DB(JPA) / Rest
* [GameAPI](gameapi/readme.MD) : DB(JPA) / Rest
* [FrontWeb](front-web/readme.MD) : React Web Application
* [Common](library/readme.MD) : Common Depency (Document or AKKA or ANY)


## Documents(KOR):
* WIKI - http://wiki.webnori.com/display/webfr/Spring+Cloud+MicroService



