FROM openjdk:21-jdk
LABEL authors="eddyk"
COPY ./target/management-0.0.1-SNAPSHOT.jar /app/management-0.0.1-SNAPSHOT.jar
EXPOSE 8081

CMD ["java", "-jar", "/app/management-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]