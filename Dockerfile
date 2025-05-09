# Build stage
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/currencyconverter-*.jar app.jar

# Environment variables (override at runtime)
ENV CURRENCY_API_KEY=default_key_placeholder
ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}
ENTRYPOINT ["java", "-jar", "app.jar"]