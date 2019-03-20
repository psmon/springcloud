docker stop configservice

docker rm configservice

docker build -t psmon.configservice .

docker run --net devnet --ip 172.19.0.30 -it -d -p 8888:8888 -e "SPRING_PROFILES_ACTIVE=dock-local" \
    -e SPRING_CLOUD_CONFIG_URI=https://github.com/psmon/mycloudconfig.git --name configservice psmon.configservice


spring.cloud.config.uri