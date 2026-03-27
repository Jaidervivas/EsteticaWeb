# Paso 1: Construcción con Java 17 y Ant
FROM eclipse-temurin:17-jdk AS build
RUN apt-get update && apt-get install -y ant
COPY . .
RUN ant jar

# Paso 2: Ejecución
FROM eclipse-temurin:17-jre
# Copiamos el archivo .jar generado por Ant
COPY --from=build /dist/*.jar app.jar
EXPOSE 8080
# La clave es "-Djava.awt.headless=true" para que no busque pantalla
CMD ["java", "-Djava.awt.headless=true", "-jar", "app.jar"]