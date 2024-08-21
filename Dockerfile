FROM openjdk:21-jdk
LABEL authors="eddykoko"
COPY target/inventory-management.jar /app/inventory-management.jar
EXPOSE 8081

CMD ["java", "-jar", "/inventory-management.jar", "--spring.profiles.active=prod"]