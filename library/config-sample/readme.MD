# centralized-configuration 

## fist common config : application.yml

## config sample : {appname}.yml

  message: "hello, spring cloud -default"
  
  ---
  spring:
    profiles: local
  
  message: "hi, spring cloud -local"
  
  ---
  spring:
    profiles: dock-local
  
  message: "hello, spring cloud -doc local"

## last

application.yml + {appname}.yml


## select spring config
	mvn spring-boot:run -Dspring-boot.run.profiles=local

## update config
	curl localhost:8080/actuator/refresh -d {} -H "Content-Type: application/json"
