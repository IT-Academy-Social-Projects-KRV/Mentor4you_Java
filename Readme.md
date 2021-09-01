GET
http://localhost:8080/system/add 

localhost:8080/system/add
#Insert data into Roles GroupServices Accounts Users Mentors
#result  -->>  1 admin \\ 3 moderators \\ 15 mentors


# Project launch:Linc to swagger
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config/

# Project launch:
## Presets: :desktop_computer::electric_plug::floppy_disk:
* ####  Git and Docker are installed. 
* #### The frontend part is downloaded:  https://github.com/IT-Academy-Social-Projects-KRV/Mentor4you_Angular.git
* #### The backend part is downloaded:    https://github.com/IT-Academy-Social-Projects-KRV/Mentor4you_Java.git

## Execution:
### Open the terminal in the frontend folder, run the command and wait for its execution:	:computer:
    docker build -t frontend .
### Open the terminal in the backtend folder, and execute commands consistently:	:computer:
    1. mvn clean package -DskipTests
    2. docker-compose -p mentor4you up
    
## Expected result: The project "mentor4you" is started.	


### Access is made from the local server to the following addresses:
* #####   Frontend:     http://localhost:4200/
* #####   Backend:      http://localhost:8080/
* #####   Adminer:      http://localhost:8082/
  
### Adminer setting for databases access:
#####  - MySQL
      Server:	mysql
      User:	mentor4you
      Password:	1234
      Database:	mentor4you_db
      
#####  - MongoDB
      Server:	mongo_db
      User:	mentor4you
      Password:
      Database:	mentor4you_db 
      
