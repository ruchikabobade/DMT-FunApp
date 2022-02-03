Steps to run TODO application

Pre-requisites:
1. Maven installed
2. JDK installed
3. MySql installed (or docker image can be used)

Below steps uses docker to bring up MySql server. If not using docker, you can follow https://docs.oracle.com/en/java/java-components/advanced-management-console/2.21/install-guide/mysql-database-installation-and-configuration-advanced-management-console.html#GUID-12323233-07E3-45C2-B77A-F35B3BBA6592 to setup MySql

Steps:
1. Open the command line in the application and install all the maven dependencies by running `mvn install`
2. Once all the dependencies are installed, follow below steps to bring up MySql using docker image
3. Make sure docker desktop is running. Run `docker pull mysql` to pull docker image
4. Run docker image `docker run --name local-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql:latest`
5. Login to docker container to access MySql server by `docker exec -it local-mysql /bin/bash`
6. Then execute `mysql -u root -p`, enter password as `password`
7. Create TodoDB using `create database todoDB;`
8. Once database is set, run the application using maven command `mvn spring-boot:run`
9. To test the APIs, import postman collection and env from the folder postman