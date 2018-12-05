# MicroService Kubernetes for Deploy 

# Pre-Requirements    
    docker - https://docs.docker.com/install/
    minshift - https://github.com/minishift/minishift/releases          
    copy minshift.exe C:\Program Files\Docker\Docker\resources\bin
    
    $ kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default
    $ minishift addons enable admin-user    


# Deploy
    $ kubectl apply -f kubernetes\config-service.yaml
    $ kubectl apply -f kubernetes\eureka-serviceregistry.yaml
    $ kubectl apply -f kubernetes\lobbyapi-deployment.yaml

## Ribon
All the microservices are deployed on Kubernetes. Now, it's worth it to discuss some aspects related to inter-service communication


links:
* https://dzone.com/articles/quick-guide-to-microservices-with-kubernetes-sprin
* https://github.com/fabric8io/spring-cloud-kubernetes