FROM ubuntu:22.04 AS build

LABEL maintainer="andre-carbajal"

RUN apt-get update && apt-get install -y openjdk-21-jdk

COPY target/*.jar /app/app.jar

ENV DB_HOST=''
ENV DB_PORT=''
ENV DB_DATABASE=''
ENV DB_USERNAME=''
ENV DB_PASSWORD=''
ENV APP_BASE_URL=''

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod", "/app/app.jar"]
