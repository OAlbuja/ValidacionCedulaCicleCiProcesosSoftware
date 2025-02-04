# Etapa 1: Construcción de la aplicación en Maven
FROM maven:3.8.8-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiar los archivos del proyecto al contenedor
COPY . .

# Compilar el proyecto y generar el JAR
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final ligera con OpenJDK
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiar solo el JAR desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
