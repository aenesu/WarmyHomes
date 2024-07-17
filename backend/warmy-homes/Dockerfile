# Stage 1: Build the application
FROM maven:3.8.5-openjdk-11 AS build
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:11-jdk
VOLUME /tmp

# Set the working directory
WORKDIR /target

# Copy the JAR file from the build output to the image
COPY --from=build /target/warmy-homes-0.0.1-SNAPSHOT.jar app.jar

# Set the PORT environment variable
ENV PORT 8080

# Start the application
ENTRYPOINT ["java","-Dserver.port=${PORT}", "-jar", "/app.jar"]