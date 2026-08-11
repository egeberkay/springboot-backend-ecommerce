# 🚀 Spring Boot E-Commerce RESTful API (Northwind)

🛒 Northwind E-Commerce Backend

A backend e-commerce project developed with Spring Boot, built on the classic Northwind database. It was created for learning and self-improvement purposes.

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


## 📁 Project Folder Structure (Layered Architecture)

Our project is built on Layered Architecture to increase code maintainability and readability.

```text
📦 src
 ┣ 📂 main
 ┃ ┣ 📂 java
 ┃ ┃ ┗ 📂 kodlamaio.northwind
 ┃ ┃   ┣ 📂 apicontrollers     # RESTful API endpoints exposed to the outside world
 ┃ ┃   ┣ 📂 business           # Layer where business rules are executed (Services & Managers)
 ┃ ┃   ┃ ┣ 📂 abstracts        # Service interfaces
 ┃ ┃   ┃ ┗ 📂 concretes        # Business rule implementations
 ┃ ┃   ┣ 📂 core               # Project-independent, universal core codes
 ┃ ┃   ┃ ┣ 📂 dataAccess       # Common database operations
 ┃ ┃   ┃ ┣ 📂 entities         # Common entities (User, Role, etc.)
 ┃ ┃   ┃ ┣ 📂 security         # Spring Security and encryption configurations
 ┃ ┃   ┃ ┗ 📂 utilities        # Helper tools (SuccessResult, ErrorResult, etc.)
 ┃ ┃   ┣ 📂 dataAccess         # Layer communicating with the database (Repository/Dao)
 ┃ ┃   ┃ ┣ 📂 abstracts
 ┃ ┃   ┃ ┗ 📂 concretes
 ┃ ┃   ┣ 📂 entities           # Object representations of database tables (ORM)
 ┃ ┃   ┃ ┣ 📂 abstracts
 ┃ ┃   ┃ ┣ 📂 concretes        # Concrete entities like Product, Category, etc.
 ┃ ┃   ┃ ┗ 📂 dtos             # Data Transfer Objects (DTOs)
 ┃ ┃   ┗ 📜 NorthwindApplication.java  # Spring Boot's main execution class
 ┃ ┗ 📂 resources              # Configuration files (application.properties, etc.)
 ┗ 📂 test                     # Unit and integration tests
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

## Getting Started

Follow these steps to set up the project locally.

### Prerequisites

List the software, compilers, or tools required before installing:
- Java Development Kit (JDK 17 or higher)
- PostgreSQL
- Apache Maven

### Installation

1. Clone the repository:
   ```bash
   git clone [https://github.com/username/northwind.git](https://github.com/username/northwind.git)

2.   Navigate to the project directory:

 Bash
cd northwind

3. Configure the database:
Open the src/main/resources/application.properties file and enter your local PostgreSQL credentials:

Properties
spring.datasource.url=jdbc:postgresql://localhost:5432/northwind
spring.datasource.username=YOUR_DATABASE_USERNAME
spring.datasource.password=YOUR_DATABASE_PASSWORD
spring.jpa.hibernate.ddl-auto=update

4. Install dependencies:

Bash
mvn clean install
