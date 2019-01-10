# NewsAppBoilerplateJava

## Code checkout 
Checkout the project using proper git credentials.

git clone "https://gitlab-cts.stackroute.in/Adarsh.GUnnithan/NewsApplication-Java-BoilerPlate.git"

## Backend Local setup
1. Navigate to server folder
    cd NewsApplication-Java-BoilerPlate/Server/
2. Execute enviroment varibles
    source ./env.sh
3. Run application as spring boot
    cd target
    java -jar news-app-0.0.1-SNAPSHOT.jar

## Authentication server setup

1. Naviagate to AuthenticateService folder
    cd NewsApplication-Java-BoilerPlate/AuthenticateService/
2. Execute enviroment varibles
    source ./env.sh
3. Run project as spring boot   
    cd target
    java -jar security-0.0.1-SNAPSHOT.jar

##Front end setup
1. Navigate to frontend application folder
    cd UI
2.Install all node and angular dependencies
    npm install
3.Build app with production flag
    ng build --prod
4.Run application
    ng serve

## Dockerise app
1. Keep local mysql server down to avoid port conflicts
    sudo service mysql stop
2. Create mysql image as in docker compose with same set of enviroment varibles
3. Run docker compose
    sudo docker-compose up

## Run automated end to end test
1. Ensure backend services are up and running
2. ng e2e  command can be used from frontend project folder

## Application Users and security
There are 2 types of users in the system. 1.Admin 2.User 
User with userid "admin" will be assigned with Admin role and previlages.JWT tokens are used for security