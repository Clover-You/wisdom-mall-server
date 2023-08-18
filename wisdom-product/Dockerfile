FROM openjdk:22-slim
LABEL authors="CloverYou"

COPY target/*.jar /app.jar

EXPOSE 10200

ENTRYPOINT ["java", "-jar", "/app.jar", "-Xmx300m", "-Xms300m", "-Xmn200m"]