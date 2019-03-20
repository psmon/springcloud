docker stop akkacluster

docker rm akkacluster

docker build -t psmon.akkacluster .

docker run --net devnet --ip 172.19.0.33 -it -d -e "SPRING_PROFILES_ACTIVE=dock-local" ^
--hostname 172.19.0.33 ^
-e "SPRING_CLOUD_CONFIG_URI=http://172.19.0.30:8888" --name akkacluster psmon.akkacluster
