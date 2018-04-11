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

## Technologies
[![Kotlin][kotlin_logo]][kotlin_link]

[![Spring Boot][springboot_logo]][springboot_link]

[![REST API][restapi_logo]][restapi_link]

[![Gradle][gradle_logo]][gradle_link]

[![Docker][docker_logo]][docker_link]

[kotlin_link]: https://kotlinlang.org/
[kotlin_logo]: kotlin-logo.png

[springboot_link]: https://projects.spring.io/spring-boot/
[springboot_logo]: springboot-logo.png

[restapi_link]: https://restfulapi.net/
[restapi_logo]: rest-api-logo.png

[gradle_link]: https://gradle.org/
[gradle_logo]: gradle-logo.png

[docker_link]: https://www.docker.com/
[docker_logo]: docker-logo.png
