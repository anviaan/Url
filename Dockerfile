FROM eclipse-temurin-21

LABEL maintainer="andre-carbajal"

COPY target/*.jar /app/app.jar

EXPOSE 8080

ENV DB_HOST=''
ENV DB_PORT=''
ENV DB_DATABASE = ''
ENV DB_USERNAME=''
ENV DB_PASSWORD=''
ENV APP_BASE_URL=''

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod", "/app/app.jar"]