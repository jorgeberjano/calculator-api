# Script para crear la imagen del contenedor docker
FROM openjdk:11-jdk
LABEL VERSION=0.1.0
COPY target/*.jar /app/app.jar
WORKDIR /app
EXPOSE 80
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
