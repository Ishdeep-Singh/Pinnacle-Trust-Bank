# Use a base image with Maven installed
FROM maven:3.8.5-jdk-11 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper, mvnw, and pom.xml
COPY .mvn/ .mvn
COPY mvnw ./
RUN ls -la
COPY pom.xml ./

# Ensure the Maven wrapper is executable
RUN chmod +x mvnw

# Go online to download dependencies
RUN /app/mvnw dependency:resolve

# Copy the source code
COPY src ./src

# Build the application
RUN ./mvnw package -DskipTests
