# Spring Cloud for Distributed applications


## Goals
- Distributed Micro Game(or Other) Services Using Spring Cloud

    In the domain-driven design, the domain is first built and the infrastructure is next. However, 
    we are aiming to build a frame that can contain various microdomains.

Concept:
* Service Discovery : Eureka
* Gateway Service : Zulu
* Minimal monitoring : 
* Peer-to-peer: New nodes can contact existing peers, be notified about other peers, and fully integrate themselves into the network without any configuration changes.
* No single point of failure/bottleneck: multiple nodes are able to service requests, increasing throughput and fault-tolerance.
* Connect with other streams : alpakka

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

Profile for Local:
- EurekaServiceregistry: local
- ConfigService : local
- AkkaCluster(Seed) : local
- DataCenter : local-h2
- AccountApi1 : local-h2
- AccountApi2 : local-h3
- LobbyApi : local

Deployment goal:
- Run all locally without a docker
- Run locally through integration of the dockers
- Drive the docker directly to AWS
- Deliver to BareMetal without AWS

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

## MicroService Sample - There is no domain goal here, but you can use the sample code to add new domain ideas and create services.
* [DataCenter](datacenter) : Distributed data aggregation, centralized service that can mapreduce the data of a distributed application
* [AccountAPI](accountapi) : Manage User account or items. - JPA or Native Query
* [LobbyAPI](lobbyapi) : Provide services beyond games, including navigating games and entering. - Rest and Websocket
* [GameAPI](gameapi) : Realtime messages are mainly processed. Services for playing games - Websocket with Actor
* [FrontWeb](front-web) : Modern web applications to be displayed to users -HTML5 with React

## UnitTestTools
A classic CRUD unit test requires a lot of code or is almost useless to reflect business logic. We will prepare various test tools to verify the actual business logic.

* [LobbyAPI-TestTools](lobbyapi/src/test)

## Fast Data
In our service, many messages and data will be generated, and we will acquire it quickly and deliver the data to various advanced analysis tools to try visualization. This is a concept opposite to long-cycle batch processing.

* [FastData](fastdata.md)

## MessageSystem in APP for Akka 

We use AKKA as a base and Akka Stream connect with other Stream(Kafka,RabbitMQ,ETC)
One of the characteristics of AkkaSystem is that we can embed message systems in our services.

* [Akka](library/akka.MD)

## Documents(KOR):
* WIKI - http://wiki.webnori.com/display/webfr/Spring+Cloud+MicroService



