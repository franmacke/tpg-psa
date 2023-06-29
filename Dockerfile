# Utilizar la imagen de Java 17 como base
FROM adoptopenjdk:17-jdk-hotspot

# Establecer el directorio de trabajo en la aplicación
WORKDIR /app

# Copiar el archivo pom.xml para descargar las dependencias
COPY pom.xml .

# Descargar las dependencias de Maven
RUN mvn dependency:go-offline -B

# Copiar el resto del código fuente a la imagen
COPY src ./src

# Compilar la aplicación con Maven
RUN mvn clean package -DskipTests

# Establecer las variables de entorno para la base de datos H2
ENV DB_URL=jdbc:h2:mem:testdb
ENV DB_USERNAME=sa
ENV DB_PASSWORD=password

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "target/tpg-psa.jar"]
