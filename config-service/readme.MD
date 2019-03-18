# Config Service

![enter image description here](../library/doc-res/spring-config.png)

Centralizing and managing the settings used in an application has several advantages

It must be layered and managed.

default url :  http://localhost:8888

## Application Configuration Sequence
- Load Application : appication.properties
- Access ConfigServer and Register DiscoveryServer : spring.application.name={appname} ,spring.cloud.config.uri=http://localhost:8888
- Load Default Profile from application.yml (git base)
- Environment increment setting + {appname}.yml
- Applied before Web service listener based on profile, including port on server


## Build By Docker
* build : docker build -t psmon.configservice .
* run : docker run --net psmonlocal --ip 172.18.1.1 -it -d -p 18888:8888 -e "SPRING_PROFILES_ACTIVE=dock-local" --name configservice psmon.configservice
* rerun : docker restart configservice 
* stop : docker stop configservice


## Documents(KOR):
* WIKI - http://wiki.webnori.com/display/webfr/Centralized+Configuration


 

