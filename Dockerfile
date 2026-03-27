# Paso 1: Construcción
FROM eclipse-temurin:17-jdk AS build
RUN apt-get update && apt-get install -y ant
COPY . .
RUN ant jar

# Paso 2: Ejecución
FROM eclipse-temurin:17-jre
COPY --from=build /dist/*.jar app.jar
EXPOSE 8080
# El comando ahora incluye "-Djava.awt.headless=true"
CMD ["java", "-Djava.awt.headless=true", "-jar", "app.jar"]