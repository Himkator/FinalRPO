F1Academy – Educational Platform for Formula 1 Studies
📌 Project Description

F1Academy is a Spring Boot–based educational platform designed to provide structured learning for Formula 1 enthusiasts.
The system allows students to enroll in professional F1-related courses, team managers to create and manage educational content, and administrators to control users and system security.

The project demonstrates a clean layered architecture, role-based access control, RESTful APIs, DTO usage, database migrations, and unit testing.

🎯 Goal & Objectives
🎯 Goal

To build a secure and scalable backend system for managing Formula 1 educational courses, enrollments, and users with strict role-based access control.

✅ Objectives

Implement authentication and authorization using Spring Security + JWT

Support multiple roles: ADMIN, MANAGER, STUDENT

Allow managers to create and manage F1 teams and courses

Allow students to enroll in courses and track progress

Ensure clean architecture using DTOs and MapStruct

Use database migrations for schema management

Provide documented REST APIs for testing and verification

🧩 Roles & Permissions
Role	Description
ADMIN	Manages users, blocks users, system control
MANAGER	Creates F1 teams, courses, lessons
STUDENT	Enrolls in courses, views lessons, tracks progress
🏗️ Application Architecture

The project follows layered architecture:

Controller → Service → Repository → Database
            ↓
           DTO
            ↓
         MapStruct

Layers:

Controller – REST API endpoints (no business logic)

Service – Business logic

Repository – Database access (Spring Data JPA)

Entity – JPA entities

DTO – Request & Response objects

Mapper – MapStruct mappers

Security – JWT, filters, role-based access

🗃️ Database & Migrations

Database: PostgreSQL

ORM: Hibernate / JPA

Migrations: Liquibase

All tables are created and filled using Liquibase changelogs

Initial demo data is included (users, teams, courses, lessons, enrollments)

📦 Main Entities

User

F1Team

Course

Lesson

Enrollment

Each entity:

Has Request & Response DTOs

Uses MapStruct for mapping

Has a dedicated Service & Controller

Is covered by unit tests

🔐 Security

Authentication: JWT

Password encryption: BCrypt

Stateless sessions

Role-based endpoint protection

Custom UserDetailsService

🧪 Testing

Unit tests implemented using:

JUnit 5

Mockito

Each service is tested for:

Data retrieval

Data persistence

Business logic

📬 API Documentation & Testing

All APIs are tested using Postman

Postman Collection includes:

Authentication requests

CRUD operations

Role-based access examples

Request & response DTO examples

📁 Postman collection is included in the repository.

🚀 How to Run the Project
1️⃣ Clone the repository
git clone https://github.com/your-username/F1Academy.git
cd F1Academy

2️⃣ Configure database

Edit application.yml or application.properties:

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/f1academy
    username: postgres
    password: postgres

3️⃣ Run migrations

Liquibase will run automatically on application startup.

4️⃣ Start the application
mvn spring-boot:run

5️⃣ Access Swagger (if enabled)
http://localhost:8080/swagger-ui.html

📁 Repository Structure
src/main/java
 ├── configs
 ├── controllers
 ├── db
 │   ├── entities
 │   ├── enums
 │   └── repositories
 ├── dto
 ├── mappers
 ├── security
 ├── services
 └── FinalSoftApplication.java

👨‍💻 Technologies Used

Java 17

Spring Boot

Spring Security

JWT

Spring Data JPA

Hibernate

MapStruct

Liquibase

PostgreSQL

Maven

JUnit & Mockito

🎤 Project Defense Notes

During defense, the team demonstrates:

System goal and business logic

Database structure

Layer interaction (Controller → Service → Repository)

Authentication & role-based access

API testing via Postman

Additional features (if any)
