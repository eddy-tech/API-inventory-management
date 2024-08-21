FROM openjdk:21-jdk
LABEL authors="eddykoko"
COPY ./target/management-0.0.1-SNAPSHOT.jar  /usrs/app/
WORKDIR /usr/app/
EXPOSE 8081

CMD ["java", "-jar", "management-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]