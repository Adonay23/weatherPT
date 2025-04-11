
FROM eclipse-temurin:17-jre-jammy

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR generado por Maven
COPY target/forecast-0.0.1-SNAPSHOT.jar app.jar

# Puerto que expone tu aplicación (ajusta al de tu application.properties)
EXPOSE 8082

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]