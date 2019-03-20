docker stop configservice

docker rm configservice

docker build -t psmon.configservice .

docker run --net devnet --ip 172.19.0.30 -it -d -p 8888:8888 -e "SPRING_PROFILES_ACTIVE=dock-local" ^
-e "SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/psmon/mycloudconfig.git" ^
-e "SERVICE_URL_DEFAULT_ZONE=http://172.19.0.31:8761/eureka/" ^
--hostname 172.19.0.30 ^
-e "MY_HOST_IP=172.19.0.30" --name configservice psmon.configservice
