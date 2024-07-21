# Build stage
FROM maven:3.8.3-openjdk-11 AS build
WORKDIR /app
COPY backend/warmy-homes/pom.xml /app/
RUN mvn dependency:go-offline -B
COPY backend/warmy-homes/src /app/src
RUN mvn clean package -DskipTests

# Package stage
FROM openjdk:11-jdk
WORKDIR /app
COPY --from=build /app/target/warmy-homes-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
