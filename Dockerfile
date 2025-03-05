FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY build/libs/the-brew-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
