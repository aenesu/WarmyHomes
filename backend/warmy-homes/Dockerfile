# Build stage
FROM maven:3.8.3-openjdk-11 AS build
WORKDIR /app

# Projeyi build context'e kopyalayın
COPY warmy-homes/pom.xml /app/
COPY warmy-homes/src /app/src

# Maven bağımlılıklarını indir
RUN mvn dependency:go-offline -B

# Maven ile projeyi build edin
RUN mvn clean package -DskipTests

# Package stage
FROM openjdk:11-jdk
WORKDIR /app

# JAR dosyasını kopyalayın
COPY --from=build /app/target/warmy-homes-0.0.1-SNAPSHOT.jar app.jar

# Uygulama portunu açın
EXPOSE 8080

# Uygulamayı başlatın
ENTRYPOINT ["java", "-jar", "app.jar"]
