REM Creating logs directory
mkdir logs
REM Creating a virtual machine for docker called 'grant-machine'
docker-machine create grant-machine
REM Next 2 docker commands for docker to work fine with windows cmd
docker-machine env --shell cmd grant-machine
ping 127.0.0.1 -n 2 > nul
@FOR /f "tokens=*" %%i IN ('docker-machine env --shell cmd grant-machine') DO @%%i
REM Your docker-container's IP-address is in file called 'ip.txt'. To open the app you should call 'ip-from-file:8080'. Swagger - 'ip-from-file:8080/swagger-ui.html'
docker-machine ip grant-machine > logs/ip.txt
REM Remove 'grant_app_with_postger_container' to create a new one
docker rm --force grant_app_with_postger_container
REM Run postgres container for our database
docker run --name grant_postgres_container -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=grant -d postgres
ping 127.0.0.1 -n 6 > nul
REM Create container for our app
docker build -t grant_app_container .
REM Run a new container which contains database container and app container. In links both containers. Prints logs into 'log.txt' and errors into 'error.txt' 
docker run --name grant_app_with_postger_container --link grant_postgres_container -p 8080:8080 grant_app_container 2>logs/error.txt