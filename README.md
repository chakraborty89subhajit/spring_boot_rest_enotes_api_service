# spring_boot_rest_enotes_api_service
# eNotes API Service

A robust RESTful Backend API built with **Spring Boot 3**, **Spring Security**, and **MySQL** for managing personal notes, user authentication, and categorization.

---

## 🚀 Features

- **User Authentication & Authorization:** Secure registration and login workflows with Spring Security and role-based access.
- **Category Management:** Full CRUD operations to organize notes under custom categories.
- **Notes Management:** Create, update, view, soft-delete, and organize personal notes.
- **Favorites & To-Do Tracking:** Mark notes as favorites and keep track of actionable item lists.
- **Consistent API Responses:** Standardized JSON response wrappers across all endpoints.
- **Global Exception Handling:** Unified error handling for data validation, database constraints, and auth failures.

---

## 🛠️ Tech Stack

- **Java:** 17+ (or Java 21)
- **Framework:** Spring Boot 3.x
- **Security:** Spring Security
- **Database:** MySQL 8.0+
- **ORM / Data Access:** Spring Data JPA / Hibernate
- **DTO Mapping:** ModelMapper / MapStruct
- **Build Tool:** Maven

---

## ⚙️ Prerequisites & Setup

### 1. Database Configuration
Ensure MySQL Server is running locally or via Docker. Create the target database:

```sql
CREATE DATABASE IF NOT EXISTS enotesdb;

spring.datasource.url=jdbc:mysql://localhost:3306/enotesdb?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate Properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect


=====================================================================
git clone [https://github.com/your-username/enotes-api-service.git](https://github.com/your-username/enotes-api-service.git)
cd enotes-api-service

=====================================================================
mvn clean package
=====================================================================
mvn spring-boot:run
=====================================================================
The application will start on http://localhost:8080.📌 API Endpoints OverviewAuth & User (/api/v1/auth, /api/v1/user)MethodEndpointDescriptionPublic / ProtectedPOST/api/v1/user/Register a new userPublicPOST/api/v1/user/loginAuthenticate user & get responsePublicCategories (/api/v1/category)MethodEndpointDescriptionPublic / ProtectedGET/api/v1/category/categoriesRetrieve all categoriesProtectedPOST/api/v1/category/Create a new categoryProtectedPUT/api/v1/category/{id}Update an existing categoryProtectedDELETE/api/v1/category/{id}Delete a categoryProtectedNotes (/api/v1/notes)MethodEndpointDescriptionPublic / ProtectedGET/api/v1/notes/Fetch user notesProtectedPOST/api/v1/notes/Save a new noteProtected
=====================================================================
Register User (POST /api/v1/user/)
{
  "firstName": "Subhajit",
  "lastName": "Chakraborty",
  "email": "subhajit@gmail.com",
  "password": "your_secure_password",
  "mobNo": "9012345670",
  "roles": [
    { "id": 2 }
  ]
}
======================================================================
User Login (POST /api/v1/user/login)
JSON
{
  "email": "subhajit@gmail.com",
  "password": "your_secure_password"
}
=======================================================================
License
This project is licensed under the MIT License - see the LICENSE file for details.


Would you like to add a section for JWT authentication or Swagger/OpenAPI documentatio
