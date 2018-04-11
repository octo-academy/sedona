FROM openjdk:8-jre-alpine

EXPOSE 8080

COPY /build/libs/sedona-backend-0.1.0.jar /opt/app/app.jar

WORKDIR /opt/app/
CMD java -jar app.jar
