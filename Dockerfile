FROM maven:3.9.8-eclipse-temurin-21 AS build

LABEL maintainer="andre-carbajal"

WORKDIR /app

COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B

COPY ./src ./src
RUN mvn package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENV DB_HOST=''
ENV DB_PORT=''
ENV DB_DATABASE = ''
ENV DB_USERNAME=''
ENV DB_PASSWORD=''
ENV APP_BASE_URL=''

ENTRYPOINT ["java", "-Xmx512m", "-jar","-Dspring.profiles.active=prod", "/app/app.jar"]