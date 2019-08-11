FROM openjdk:8-jre-alpine

LABEL maintainer="Denis Verkhoturov mod.satyr@gmail.com"

EXPOSE 8080

COPY /build/libs/sedona-backend-0.1.0.jar /opt/app/app.jar

WORKDIR /opt/app/
ENTRYPOINT ["java", "-jar", "app.jar"]
