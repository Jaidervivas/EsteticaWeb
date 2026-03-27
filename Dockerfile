# Paso 1: Construcción con Java 17 y Ant
FROM eclipse-temurin:17-jdk AS build
RUN apt-get update && apt-get install -y ant
COPY . .
# Forzamos la construcción del JAR
RUN ant jar

# Paso 2: Ejecución
FROM eclipse-temurin:17-jre
# Copiamos el archivo con el nombre exacto que vimos en tu carpeta dist
COPY --from=build /dist/PaginaWeb.java.jar app.jar
EXPOSE 8080
# Ejecutamos en modo sin interfaz gráfica para que no falle en el servidor
CMD ["java", "-Djava.awt.headless=true", "-jar", "app.jar"]