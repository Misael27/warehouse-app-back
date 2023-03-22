# Technical solution

Price application

## Steps to run
- mvn package
- cd ./application
- mvn spring-boot:run
- Open in http://localhost:5000/swagger-ui/index.html

## Features
- Spring boot 3
- Java 17
- Hexagonal and DDD Architecture
- Open Api 3

## Solution Description

### 3 projects are used to keep the solution decoupled.

**DOMAIN** project, it is the business logic, domain entities, domain services, business rule validations, domain exceptions, and repository interfaces. This project is isolated from the spring configurations and the infrastructure that will be used, it will only take care of the business logic as much as possible. It has its own unit tests that verify that the business rules are met.

**INFRASTRUCTURE** project, there is the implementation of the repository interfaces, spring repository, the database configuration parameters and possible connections with external systems. It depends on the DOMAIN project in order to be executed.

**APPLICATION** project, it is where the spring configuration is, the definition of the beans of the domain services. The definition of the HTTP controller, the request mapping, global ControllerAdvice exception handler, mappgin from DTO to Entity and vice versa, is what is responsible for starting the application. It depends on the DOMAIN project and the INFRASTRUCTURE project to be able to execute. It has integration tests that validate the proper functioning of the application.

At the root of the project there is a pom.xml that allows the entire solution to be compiled.
