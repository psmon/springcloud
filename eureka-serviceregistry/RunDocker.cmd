docker stop eureka1

docker rm eureka1

docker build -t psmon.eureka .

docker run --net devnet --ip 172.19.0.31 -it -d -p 8761:8761 -e "SPRING_PROFILES_ACTIVE=dock-local" ^
--hostname 172.19.0.31 ^
-e "MY_HOST_IP=172.19.0.31" -e "EUREKA_URI=http://172.19.0.31:8761/eureka" --name eureka1 psmon.eureka
