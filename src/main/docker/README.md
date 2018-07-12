0. If you want to run application in docker container you should install docker first. You can do it on official site of docker. It's free.
1. You should uncomment these lines in application.properties in case of running docker:
spring.datasource.url=jdbc:postgresql://grant_postgres_container:5432/grant
spring.datasource.username=postgres
spring.datasource.password=postgres
2. To start the application you need to do 'mvn clean install'.
3. Then go to docker package.
4. Start start.bat.