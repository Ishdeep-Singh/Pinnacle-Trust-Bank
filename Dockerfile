# Stage 1: Use an image to install utilities and build the WAR
FROM openjdk:21-jdk-slim AS builder

# Set the working directory
WORKDIR /app

# Copy Maven wrapper and configuration files
COPY .mvn/ .mvn
COPY mvnw ./
COPY pom.xml ./

# Install dos2unix for line-ending conversion
RUN apt-get update && apt-get install -y dos2unix && dos2unix mvnw

# Ensure Maven wrapper is executable
RUN chmod +x mvnw

# Download dependencies
RUN bash ./mvnw dependency:resolve

# Copy the source code
COPY src ./src

# Build the WAR file (not JAR) and exclude embedded Tomcat
RUN bash ./mvnw clean package -DskipTests -Pwar -e

# Stage 2: Copy built artifacts into a minimal runtime image (if needed for other tasks)
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the WAR from the previous stage
COPY --from=builder /app/target/*.war /app/app.war
