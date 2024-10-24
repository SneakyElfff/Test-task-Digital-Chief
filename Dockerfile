FROM openjdk:21-jdk

WORKDIR /app

COPY target/ElasticDataSearcher-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]