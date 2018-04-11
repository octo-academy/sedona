# Sedona: backend
Sedona backend is REST API project for one of the HTML Academy projects of the same name.

## Run application
Make sure you have [docker][docker_link] installed and follow the steps.
Compile and build and application:
```bash
./gradlew clean build
```
Build an image:
```bash
docker build -t sedona-server .
```
and then run container mapping exposed port of the container on the port of host machine
you want the application to listen:
```bash
docker run -d -p 80:8080 sedona-server
```

[docker_link]: https://www.docker.com/
