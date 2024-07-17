# Use an official OpenJDK image as the base image
FROM openjdk:11-jdk

VOLUME /tmp


# Set the working directory
ARG JAR_FILE=target/warmy-homes-0.0.1-SNAPSHOT.jar

# Copy the JAR file from the build output to the image
COPY ${JAR_FILE} app.jar

ENV PORT 8080

# Start the application
ENTRYPOINT ["java","-Dserver.port=${PORT}", "-jar", "/app.jar"]






