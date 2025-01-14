# Paso 1: COMPILAR CON MAVEN
# Usamos una imagen de Maven con Corretto 17 para compilar el proyecto.
# En esta etapa se construye el JAR de la aplicación, omitiendo las pruebas.
FROM maven:3.9.9 AS builder
# Establecemos el directorio de trabajo en /app
WORKDIR /app
# Copiamos el archivo pom.xml y el código fuente al contenedor
COPY pom.xml .
COPY src ./src
# Verificamos que el directorio /app esté vacío en el contenedor
RUN ls /app
# Ejecutamos Maven para compilar el proyecto y generar el JAR,
# omitiendo las pruebas (-Dmaven.test.skip=true).
RUN mvn clean package -Dmaven.test.skip=true
# Paso 2: CREAR EL CONTENEDOR Y EJECUTAR EL JAR
# Usamos una imagen de OpenJDK 17 basada en Alpine para crear un contenedor más ligero.
FROM openjdk:17-oracle
# Definimos el mantenedor del contenedor (puedes cambiar el correo si es necesario)
LABEL maintainer="rod@gmail.com"
# Establecemos el directorio de trabajo en /workspace
WORKDIR /workspace
# Verificamos que el directorio /workspace esté vacío en el contenedor
RUN ls /workspace
# Copiamos el archivo JAR desde el contenedor builder y lo renombramos como app.jar
COPY --from=builder /app/target/cineflux*.jar app.jar
# Configuramos el punto de entrada del contenedor para ejecutar el JAR con Java
ENTRYPOINT exec java -jar /workspace/app.jar