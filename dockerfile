# Etapa 1: build
FROM gradle:8.7-jdk21 AS build
WORKDIR /app

# Copiamos todo
COPY . .

# Construimos el jar (sin tests para acelerar)
RUN gradle build -x test

# Etapa 2: runtime
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copiamos el jar generado
COPY --from=build /app/build/libs/*.jar app.jar

# Puerto (Render usa PORT env var)
EXPOSE 8080

# Comando de ejecución
ENTRYPOINT ["java", "-XX:+UseSerialGC", "-Xms128m", "-Xmx256m", "-jar", "app.jar"]