# Spring Cloud for Distributed applications

## Legacy System
It is part of a legacy service that has been configured and operated very long ago.

![image](library/doc-res/oldsystem.png)

## Goal
Distributed Micro Game Services Using Spring Cloud

-It has tried to implement sample functions that can be applied in practice.

![Alt text](library/doc-res/spring-cloud-arc.png)

## Launch Order
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

## Discovery Server

![image](library/doc-res/eureka-preview.png)

All micro-services are monitored and managed.
This is useful for how each application recognizes each other to create a strategy for how to dynamically distribute and respond to failures.



## Module
* [ConfigService](config-service) : Centerlized config - [Sample](/library/config-sample) -Sprint Cloud Config
* [DiscoveryService](eureka-serviceregistry) : Manage distributed applications -Spring Cloud Eureka
* [EdgeService](edge-service) : Dynamic LoadBlancer - Spring Cloud Zulu
* [libary](library) : Simple Depency(Only Shared POJO) and Third party Tools
* [Messaging](library/akka.MD) : We use AKKA as a base and Akka Stream connect with other Stream(Kafka,RabbitMQ,ETC)

## MicroService
* [DataCenter](datacenter) : Distributed data aggregation, centralized service that can mapreduce the data of a distributed application
* [AccountAPI](accountapi) : Manage User account or items. - JPA
* [LobbyAPI](lobbyapi) : Provide services beyond games, including navigating games and entering. - Rest and Websocket
* [GameAPI](gameapi) : Realtime messages are mainly processed. Services for playing games - Websocket with Actor
* [FrontWeb](front-web) : Modern web applications to be displayed to users -HTML5 with React
* [url:port/swagger-ui.html](http://127.0.0.1:9000/swagger-ui.html) : All APIs support APIDOCUMENT automation.

## UnitTestTools
A classic CRUD unit test requires a lot of code or is almost useless to reflect business logic. We will prepare various test tools to verify the actual business logic.

* [LobbyAPI-TestTools](lobbyapi/src/test)

## Fast Data
In our service, many messages and data will be generated, and we will acquire it quickly and deliver the data to various advanced analysis tools to try visualization. This is a concept opposite to long-cycle batch processing.

* [FastData](fastdata.md)

## Akka with DDD in Spring

Spring has a web total solution, and AKKA has a total solution for message processing. It may be a difficult attempt to harmonize the two things, but it will be a good experience and we will prepare some samples.

* [Akka-Actors-DomainDrivenDesign](https://www.infoq.com/articles/Reactive-Systems-Akka-Actors-DomainDrivenDesign)
* [Akka-Actors-DomainDrivenDesign](https://www.slideshare.net/Lightbend/using-the-actor-model-with-domaindriven-design-ddd-in-reactive-systems-with-vaughn-vernon)
* [SpringAkkaEventSourcing](https://mromeh.com/2018/04/27/spring-boot-akka-event-sourcing-starter-part-1/)
* [more](https://www.google.co.kr/search?newwindow=1&source=hp&ei=jQNCXIuxHYjhvASs1JmQAQ&q=spring+akka+ddd&btnK=Google+Search&oq=spring+akka+ddd)

## Documents(KOR):
* WIKI - http://wiki.webnori.com/display/webfr/Spring+Cloud+MicroService



