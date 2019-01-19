# Tools and Other Concept

## Dependency Goals

### Good
![goal](doc-res/def-simple.png)

### Bad
![goal](doc-res/def-complex.png)

### 
strategy:
* Simplified dependencies through spring boot -https://start.spring.io/
* Do not share entity. DB dependency sharing is a hurdle in micro-services.
* Share Only POJO in MicroServices. By restoring pure messages, We can play what we want. -CQRS,EventSourcing

## Deploy Goals
* Local for Allin-One Service
* DockerCompose in Local and Cloud for Test
* Kubernetes in Cloud for Proud
* Supports various environments through centralized setting - [more detail](../config-service)


## Docker-First Local Network Setting for MAC
    docker>sudo ./networksetting.sh

## Docker Commands
* docker-compose up --build
* docker-compose down --rmi local -v --remove-orphans


## Mysql-RUN
    docker/mysql>docker-compose up --build

## KAFKA-RUN
    docker/mysql>docker-compose up --build


## Our APP Run
* BuildAndRun : {APPPATH}> dockbuild.cmd


## Kubernetes
* kubernetes/{APPNAME}.yaml


## Refrence Links

* [Links](links.MD) : Reference link

