# Spring Cloud for Distributed applications

## Goal
Distributed Micro Game Services Using Spring Cloud

![Alt text](library/doc-res/spring-cloud-arc.png)

## Recommend Launch Order
* config-service
* eureka-serviceregistry
* edge-service
* your micro service
    
The information is automatically updated even if the sequence changes, but the config server must be in the initial state.

## Local Run
* FullBuild: root>mvn clean install
* AppRun: root/{APPPATH}> mvn spring-boot:run -Dspring.profiles.active="local"
* Recommend : Using Spring Dashboard

Active Spring Profile : local
![Alt text](library/doc-res/spring-dashboard.png)



## Docker Run - Local
* BuildAndRun : {APPPATH}> dockbuild.cmd

## Kubernetes
* kubernetes/{APPNAME}.yaml

## Module Concept
* [ConfigService](config-service) : Centerlized config - [Sample](/library/config-sample) -Sprint Cloud Config
* [DiscoveryService](eureka-serviceregistry) : Manage distributed applications -Spring Cloud Eureka
* [EdgeService](edge-service) : Dynamic LoadBlancer - Spring Cloud Zulu
* [libary](library) : Simple Depency (Common Lib Just have POJO or Manage Lib Dependency ) - Pure Ols Plain Java Object
* [Messaging](library/akka.MD) : We use AKKA as a base and Akka Stream connect with other Stream(Kafka,RabbitMQ,ETC)

## MicroService Concept
* [AccountAPI](accountapi) : Manage User account or items. - JPA
* [LobbyAPI](lobbyapi) : Provide services beyond games, including navigating games and entering. - Rest and Websocket
* [GameAPI](gameapi) : Realtime messages are mainly processed. Services for playing games - Websocket with EventBus
* [FrontWeb](front-web) : Modern web applications to be displayed to users -HTML5 with React

## Fast Data Concept
준비중.....

## Documents(KOR):
* WIKI - http://wiki.webnori.com/display/webfr/Spring+Cloud+MicroService



