# Technical solution

Warehouse back application

## Steps to run
- mvn package
- cd ./boot
- mvn spring-boot:run
- Open in http://localhost:5000/swagger-ui/index.html

## Features
- Spring boot 3
- Java 17
- Hexagonal and DDD Architecture
- Open Api 3

## Solution Description

### 3 projects are used to keep the solution decoupled.

**DOMAIN** project, domain entities, business rule validations, domain exceptions, and repository interfaces. This project is isolated from the spring configurations and the infrastructure that will be used, it will only take care of the business logic as much as possible. It has its own unit tests that verify that the business rules are met.

**APPLICATION** project, it is the business logic, domain services. This project is isolated from the spring configurations and the infrastructure that will be used, it will only take care of the business logic as much as possible. It has its own unit tests that verify that the business rules are met.

**INFRASTRUCTURE-DATA** project, there is the implementation of the repository interfaces, spring repository, the database configuration parameters and possible connections with external systems. It depends on the DOMAIN project in order to be executed.

**INFRASTRUCTURE-REST** project. The definition of the HTTP controller, the request mapping, global ControllerAdvice exception handler, mappgin from DTO to Entity and vice versa.

**Boot** project, it is where the spring configuration is, the definition of the beans of the domain services. is what is responsible for starting the application. It has integration tests that validate the proper functioning of the application.
