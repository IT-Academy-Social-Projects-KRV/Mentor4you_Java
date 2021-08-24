GET 

http://localhost:8080/system/add 

localhost:8080/system/add

Записывает данные в Roles  GroupServices Accounts Users Mentors
* результат  -->>  1 админ \\ 2 модератора \\ 15 менторов

# Запуск проекту:
## Передустановки: встановлено Git, Docker.
* #### Завантажено фронт:  https://github.com/IT-Academy-Social-Projects-KRV/Mentor4you_Angular.git
* #### Завантажено бек:    https://github.com/IT-Academy-Social-Projects-KRV/Mentor4you_Java.git


#### Відкривши термінал в папці завантаженого проекту фронт виконати команду та дочекатись її виконання:
    docker build -t frontend .
    
#### Відкривши термінал в папці завантаженого проекту беку послідовно виконати команди:
    1. mvn clean package -DskipTests
    
    2. docker-compose -p mentor4you up
    
### Очікуваний результат: запущено групу контейнерів з загальною назвою "mentor4you"
### Доступ здійсюється з локального сервера за наступними адресами:
* #####   Frontend:     http://localhost:4200/
* #####   Backend:      http://localhost:8080/
* #####   Adminer:      http://localhost:8082/
  
#### Налаштування Adminer для доступу до баз даних:
#####  - MySQL
      Сервер:	mysql
      Користувач:	mentor4you
      Пароль:	1234
      База даних:	mentor4you_db
      
#####  - MongoDB
      Сервер:	mongo_db
      Користувач:	mentor4you
      Пароль:
      База даних:	mentor4you_db 
      
