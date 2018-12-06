# Eureka

doc : http://wiki.webnori.com/display/webfr/Discovery+Service+-+Eureka

default url :  http://localhost:8761

## Docker
* build : docker build -t psmon.eureka .
* run : docker run --net psmonlocal --ip 172.18.1.2 -it -d -p 18761:8761 -e "SPRING_PROFILES_ACTIVE=dock-local" --name eureka1 psmon.eureka
* rerun : docker restart eureka1
* stop : docker stop eureka1

## NetWork
* network : docker network create --subnet=172.18.0.0/16 psmonlocal
* docker network inspect bridge


![enter image description here](http://wiki.webnori.com/download/attachments/16646284/%EC%9C%A0%EB%A0%88%EC%B9%B41.png?api=v2)