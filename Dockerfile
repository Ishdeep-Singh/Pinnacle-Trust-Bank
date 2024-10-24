# Stage 1: Use an image to install utilities
FROM openjdk:21-jdk-slim AS builder

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper, mvnw, and pom.xml
COPY .mvn/ .mvn
COPY mvnw ./
COPY pom.xml ./

# Install dos2unix for line ending conversion
RUN apt-get update && apt-get install -y dos2unix && dos2unix mvnw

# Ensure the Maven wrapper is executable
RUN chmod +x mvnw

# Go online to download dependencies
RUN bash ./mvnw dependency:resolve

# Copy the source code
COPY src ./src

# Clean and build the application with detailed error output
RUN bash ./mvnw clean package -DskipTests -e

# Stage 2: Copy built artifacts into a minimal runtime image
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the jar from the previous stage
COPY --from=builder /app/target/*.jar /app/app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
