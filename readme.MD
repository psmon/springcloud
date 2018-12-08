# Spring Cloud for Distributed applications

## Goal
Distributed Micro Game Services Using Spring Cloud

![Alt text](library/doc-res/spring-cloud-arc.png)

## Recommend Launch Order
* eureka-serviceregistry
* config-service
* any

## Local Run
* FullBuild: root>mvn clean install
* AppRun: root/{APPPATH}> mvn spring-boot:run -Dspring.profiles.active="local"
* Recommend : Using Spring Dashboard

## Docker Run - Local
* BuildAndRun : {APPPATH}> dockbuild.cmd

## Kubernetes
* kubernetes/{APPNAME}.yaml

## Service Concept
* [ConfigService](config-service) : Centerlized config - [Sample](/library/config-sample) -Sprint Cloud Config
* [DiscoveryService](eureka-serviceregistry) : Manage distributed applications -Spring Cloud Eureka
* [AccountAPI](accountapi) : Manage User account and items. -JPA
* [LobbyAPI](lobbyapi) : Provide services beyond games, including navigating games and entering.
* [GameAPI](gameapi) : Realtime messages are mainly processed. Services for playing games -Actor Message
* [FrontWeb](front-web) : Modern web applications to be displayed to users -HTML5
* [Common](library) : Common Depency (Document or AKKA or ANY)


## Documents(KOR):
* WIKI - http://wiki.webnori.com/display/webfr/Spring+Cloud+MicroService



