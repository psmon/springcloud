mvn clean install

docker stop eureka1

docker rm eureka1

docker build -t psmon.eureka .

docker run --net psmonlocal --ip 172.18.1.2 -it -d -p 18761:8761 -e "SPRING_PROFILES_ACTIVE=dock-local" --name eureka1 psmon.eureka
