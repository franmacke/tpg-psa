# Imagen base de Java 17 openjdk con Maven
FROM maven:3.8-openjdk-17 AS build

# Copiar el código fuente y el archivo pom.xml al contenedor
COPY pom.xml /app/pom.xml
COPY src /app/src

# Establecer el directorio de trabajo
WORKDIR /app

# Imagen base de Java 17
FROM ibm-semeru-runtimes:open-17-jre-centos7 AS runtime

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado en la etapa de construcción al contenedor
COPY --from=build /app/target/tpg-psa.jar /app/tpg-psa.jar

# Exponer el puerto en el que se ejecuta la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación Java
CMD ["java", "-jar", "tpg-psa.jar"]
