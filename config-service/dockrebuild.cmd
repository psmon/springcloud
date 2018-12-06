mvn clean install

docker stop configservice

docker rm configservice

docker build -t psmon.configservice .

docker run --net psmonlocal --ip 172.18.1.1 -it -d -p 18888:8888 -e "SPRING_PROFILES_ACTIVE=dock-local" --name configservice psmon.configservice
