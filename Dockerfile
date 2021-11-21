FROM openjdk:11-slim
COPY statement-impl/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8080