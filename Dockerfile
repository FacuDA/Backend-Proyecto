FROM amazoncorretto:11-alpine-jdk
MAINTAINER Facuda
COPY target/Facuda-0.0.1-SNAPSHOT.jar facuda-app.jar
ENTRYPOINT ["java","-jar","/facuda-app.jar"]