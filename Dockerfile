# Etapa de compilación
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /build
COPY pom.xml .
# Copiar el código fuente y compilar
COPY ./ /
RUN mvn clean package -DskipTests

# Etapa final
FROM openjdk:17-jdk-slim as final
ARG environment
ENV profile $environment
WORKDIR /app
# Copiar el archivo JAR compilado desde la etapa de compilación
COPY --from=builder /build/target/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT java -jar app.jar --spring.profiles.active=$profile