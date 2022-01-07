FROM openjdk:11-jdk-alpine

WORKDIR /usr/app

COPY build/libs/*.jar ifyapp.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ifyapp.jar"]