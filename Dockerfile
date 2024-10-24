FROM eclipse-temurin:21

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# Ensure the Maven wrapper is executable
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]