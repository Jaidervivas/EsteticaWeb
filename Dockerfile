# Paso 1: Usar una imagen con Java y Ant
FROM frekele/ant:1.10.3-jdk8 AS build
COPY . .
# Construir el proyecto usando Ant
RUN ant jar

# Paso 2: Ejecutar la aplicación (Imagen actualizada)
FROM eclipse-temurin:8-jre
COPY --from=build /dist/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]