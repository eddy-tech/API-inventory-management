FROM openjdk:21-jdk
LABEL authors="eddykoko"
ADD target/inventory-management.jar inventory-management.jar
EXPOSE 8081

CMD ["java", "-jar", "/inventory-management.jar", "--spring.profiles.active=prod"]