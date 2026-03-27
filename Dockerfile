# Paso 1: Usar una imagen con Java 17 e instalar Ant
FROM eclipse-temurin:17-jdk AS build
RUN apt-get update && apt-get install -y ant
COPY . .
# Construir el proyecto
RUN ant jar

# Paso 2: Ejecutar la aplicación con Java 17
FROM eclipse-temurin:17-jre
COPY --from=build /dist/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]