# Build stage
FROM maven:3.8.3-openjdk-11 AS build
WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests

# Package stage
FROM openjdk:11-jdk
WORKDIR /app
COPY --from=build app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]