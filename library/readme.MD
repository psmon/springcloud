# Other Tools

## Mysql-FirstSetting
* docker pull mysql/mysql-server:5.6
* docker run --net psmonlocal --ip 172.18.1.10 -it -d -p 13306:3306 --name=mysql1 mysql/mysql-server:5.6
* docker logs mysql1 2>&1 | grep GENERATED
* GENERATED ROOT PASSWORD: Ik0MeP!ER2urm4JUKxyldEB@RoG
* docker exec -it mysql1 mysql -uroot -p
* mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'Ik0MeP!ER2urm4JUKxyldEB@RoG';
* SET PASSWORD FOR 'root'@'localhost' = PASSWORD('root');
* GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY PASSWORD '@password';
* GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root';
