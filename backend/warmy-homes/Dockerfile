# Use an official OpenJDK image as the base image
FROM openjdk:11-jdk

# Set the working directory
WORKDIR /target

ARG JAR_FILE=target/*.jar

# Copy the JAR file from the build output to the image
COPY target/warmy-homes-0.0.1-SNAPSHOT.jar app.jar

# Start the application
CMD ["java", "-jar", "app.jar"]
