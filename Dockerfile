
# Dockerfile

# Primera etapa: Construcción del JAR con Maven y JDK 17
FROM eclipse-temurin:17-jdk-jammy AS build

# Instala Maven manualmente (versión estable más reciente)
RUN apt-get update && \
    apt-get install -y maven && \
    mvn --version

WORKDIR /app
COPY pom.xml .
# Descarga dependencias primero (cache eficiente)
RUN mvn dependency:go-offline -B

COPY src /app/src
# Empaqueta la aplicación (omitir tests para construcción rápida)
RUN mvn package -DskipTests

# --- Segunda etapa: Imagen final liviana solo con JRE ---
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copia el JAR desde la etapa de construcción
COPY --from=build /app/target/franchise-management-*.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8080

# Comando de ejecución (usa shell form para variables de entorno)
ENTRYPOINT ["java", "-jar", "app.jar"]