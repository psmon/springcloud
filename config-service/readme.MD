# Config Service

doc : http://wiki.webnori.com/display/webfr/Centralized+Configuration

default url :  http://localhost:8888


## Docker
* build : docker build -t psmon.configservice .
* run : docker run --net psmonlocal --ip 172.18.1.1 -it -d -p 18888:8888 -e "SPRING_PROFILES_ACTIVE=dock-local" --name configservice psmon.configservice
* rerun : docker restart configservice 
* stop : docker stop configservice


![enter image description here](http://wiki.webnori.com/download/attachments/16646230/image2018-12-2_1-3-50.png?version=1&modificationDate=1543680230673&api=v2)
