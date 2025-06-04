# Etapa de build
FROM maven:3.8.7-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de runtime
FROM openjdk:17-jdk-slim
ENV JAVA_OPTS=""
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser
WORKDIR /home/appuser/app
COPY --from=build /app/target/*.jar app.jar
RUN chown -R appuser:appgroup /home/appuser/app
USER appuser
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
