# Etapa 1: Construcción
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .  
COPY src/ src/
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
