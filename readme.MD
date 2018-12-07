# Spring Cloud for Distributed applications

##Goal
Distributed Micro Services Using Docker

![Alt text](http://wiki.webnori.com/download/attachments/16646192/spring-cloud-arc.png?api=v2)

Documents:
* WIKI - http://wiki.webnori.com/display/webfr/Spring+Cloud+MicroService
* CONFIG - http://git.webnori.com/projects/SB2/repos/cloudconfig/browse

## Local Run
* FullBuild : mvn clean install
* APPRun : mvn spring-boot:run -Dspring.profiles.active="local"
* Recommend : Spring Dashboard

## Service Concept
* AccountAPI : DB(JPA) / Rest
* LobbyAPI : Rest / Websocket / DB(JPA)
* GameAPI : WebSocket , WithOut RDB
* Front : Only JavaScript ( React or Other.js) 
* COMMON : Spring Boot 2.0  ,Message to EachService(Akka Actor) , Swagger(/swagger-ui.html)
## Ref Links

Spring Cloud :
* https://spring.io/guides/gs/multi-module/
* https://spring.io/guides/gs/centralized-configuration/
* https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-ribbon.html
* http://spring.io/projects/spring-cloud-netflix
* https://www.baeldung.com/dockerizing-spring-boot-application

Docker:
* https://github.com/jonashackt/cxf-spring-cloud-netflix-docker
* https://code.visualstudio.com/docs/java/java-container
* https://subicura.com/2017/02/10/docker-guide-for-beginners-create-image-and-deploy.html
* https://docs.docker.com/engine/reference/commandline/docker/#child-commands
* http://www.littlebigextra.com/use-spring-profiles-docker-containers/

ETC : 
* https://doc.akka.io/docs/akka/2.5/cluster-usage.html
* https://www.baeldung.com/spring-boot-actuators




