# endpoint-monitoring

It runs on Java 17

1. clone this project
2. set your database connection in application.properties file
3. use schema.sql to create database tables and and user 
4. run the application with "mvn spring-boot:run" command
5. login with the user that exists in database by sending  username and password in body of POST request /user/login
6. create some endpoints to be monitored - POST /api/monitored-endpoints  (name, url, interval - in seconds)
7. explore other endpoints
