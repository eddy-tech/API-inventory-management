FROM openjdk:21-jdk AS build
WORKDIR /build
LABEL authors="eddykoko"
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package

FROM amazoncorretto:21

WORKDIR /app
COPY --from=build /build/target/inventory-management-*.jar /app/
EXPOSE 8081

CMD ["java", "-jar", "/inventory-management.jar", "--spring.profiles.active=prod"]