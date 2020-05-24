FROM openjdk:8-jdk-alpine

LABEL maintainer="paulokinjo@hotmail.com"

ARG JAR_FILE=target/RestAPISpringBoot-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /spring/app.jar

ENTRYPOINT ["java","-jar","/spring/app.jar"]
