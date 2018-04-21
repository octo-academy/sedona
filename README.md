# Sedona: backend
Sedona backend is REST API project for one of the HTML Academy projects of the same name.

## Run application
Make sure you have [docker][docker_link] installed and required environment variables set:

* `POSTGRES_USER`
* `POSTGRES_PASSWORD`
* `POSTGRES_DB`

and then run the deployment script:
```bash
./deploy
```
Deployment script also supports `--dev` flag that allows to populate required environment variables from
`environment.properties` file in the root of the project:
```bash
./deploy --dev
```

## Technologies
[![Kotlin][kotlin_logo]][kotlin_link]

[![Spring Boot][springboot_logo]][springboot_link]

[![REST API][restapi_logo]][restapi_link]

[![Gradle][gradle_logo]][gradle_link]

[![Docker][docker_logo]][docker_link]

[kotlin_link]: https://kotlinlang.org/
[kotlin_logo]: readme/kotlin-logo.png

[springboot_link]: https://projects.spring.io/spring-boot/
[springboot_logo]: readme/springboot-logo.png

[restapi_link]: https://restfulapi.net/
[restapi_logo]: readme/rest-api-logo.png

[gradle_link]: https://gradle.org/
[gradle_logo]: readme/gradle-logo.png

[docker_link]: https://www.docker.com/
[docker_logo]: readme/docker-logo.png
