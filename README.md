# Spring Boot Inventory Management

This project is used to manage articles, categories, customers and providers orders, customer, provider, sale and stock
move
This repository contains the latest source code of the Spring Boot

# Services Overview
- Article
- Category
- Cloudinary (for store pictures)
- customer, customer order and customer order line
- provider, provider order and provider order line
- enterprise
- sale and sale line
- stock movement
- user

### Tech Stack 📝
The technologies used in this project are:
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Keycloak](https://www.keycloak.org/)
- [Cloudinary](https://cloudinary.com)
- [PostgreSQL](https://www.postgresql.org/)
- [Flyway](https://www.red-gate.com/products/flyway/community/)
- [Docker](https://www.docker.com/)

# Getting Started
### How to build the backend services
**Note**: 
After clone the project on your local machine(git clone link_project), you have to make some process:
- create your own application-dev.yml file in the project path /src/main/java/com/inventory/management/resources
- Replace some variables information from application-prod.yml to application-dev.yml

  | application-prod.yml | application-dev.yml                         |
  |----------------------|---------------------------------------------|
  | ${POSTGRES_DB}       | your own database                           |
  | ${POSTGRES_USER}     | your own user                               |
  | ${POSTGRES_PASSWORD} | your own password                           |
  | ${INVENTORY_KEYCLOAK_REALM} | your own realm keycloak                     |
  | ${INVENTORY_KEYCLOAK_REALM} | your own realm keycloak                     |
  | ${INVENTORY_KEYCLOAK_SERVER_URL} | your own server url                         |
  | ${INVENTORY_KEYCLOAK_CLIENT_ID} | create your own client id in keycloak realm |
  | ${INVENTORY_KEYCLOAK_CLIENT_SECRET} | paste your own client secret of keycloak realm |
  | ${CLOUDINARY_NAME} | paste your own name cloudinary after create your account |
  | ${CLOUDINARY_API_KEY} | create your own api key and paste |
  | ${CLOUDINARY_API_SECRET} | paste your own api secret after create your api key |
- create your own devData location for db/migration of flyway
- replace profile from application.yml to dev