# 🚀 Spring Boot E-Commerce RESTful API (Northwind)

This project is a fully-featured e-commerce (Northwind) backend application developed in accordance with modern software development principles and enterprise standards. The system provides a flexible and secure RESTful API infrastructure capable of communicating with any client (Frontend) such as React.

## 🏗️ Architectural Structure (N-Tier Architecture)

The project is built with a multi-layered enterprise architecture, keeping Maintainability and Loose Coupling principles in mind:

- **Core Layer:** The heart of the project. It contains universal rules, `Result` structures (SuccessResult, ErrorDataResult, etc.), and common tools that can be integrated into any project.
- **Entities Layer:** Contains the object counterparts (ORM - Object Relational Mapping) of the database tables in the Java environment.
- **Data Access Layer:** The layer where communication with the database (PostgreSQL) is established, utilizing Spring Data JPA capabilities (DAO/Repository).
- **Business Layer:** The service layer where business rules (Business Logic) are executed, validation processes are managed, and DTO (Data Transfer Object) conversions are performed.
- **API (Controllers) Layer:** The endpoint where the outside world communicates with the system, and HTTP requests (GET, POST, etc.) are received and routed.

## 🌟 Core Features

- **Secure User Management:** User registration and login operations are performed by protecting sensitive data via DTOs.
- **Standard API Responses:** All API endpoints return responses to the client (Frontend) in a standard format (Enterprise Response pattern including `success`, `message`, and `data` fields).
- **Data Validation:** Data entering the system is checked with `@Valid` annotations at the Controller stage, preventing invalid data from overwhelming the database.
- **Dependency Injection (IoC):** Dependencies between classes are managed by Spring's Inversion of Control (IoC) container, providing a highly flexible structure.

## 🛠️ Technologies and Dependencies Used

- **Java (JDK 11+):** Main programming language
- **Spring Boot:** Application framework and rapid configuration
- **Spring Data JPA & Hibernate:** ORM (Database operations)
- **Spring Web:** REST API architecture
- **PostgreSQL:** Relational Database Management System
- **Lombok:** To reduce boilerplate code (Getter, Setter, Constructor)
- **Maven:** Project and dependency management
- **Swagger / OpenAPI (Optional):** API documentation and UI testing

## ⚙️ Installation and Setup

You can follow the steps below to run the project on your local machine.

### 1. Prerequisites
- **Java (JDK)** must be installed on your computer.
- **PostgreSQL** database must be installed and running on your local machine.

### 2. Database Configuration
Open the `src/main/resources/application.properties` file in the project and enter your local database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/northwind
spring.datasource.username=YOUR_DATABASE_USERNAME
spring.datasource.password=YOUR_DATABASE_PASSWORD
spring.jpa.hibernate.ddl-auto=update
