docker stop edgeservice

docker rm edgeservice

docker build -t psmon.edgeservice .

docker run --net devnet --ip 172.19.0.32 -it -d -p 80:8888 -e "SPRING_PROFILES_ACTIVE=dock-local" ^
--hostname 172.19.0.32 ^
-e "SPRING_CLOUD_CONFIG_URI=http://172.19.0.30:8888" --name edgeservice psmon.edgeservice
