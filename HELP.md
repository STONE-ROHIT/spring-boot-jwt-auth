 📌 GitHub Project Description

> 🔐 Spring Security Demo — User Registration & Authentication
> A lightweight Spring Boot application showcasing secure user registration and Basic Authentication using Spring Security. Built with a PostgreSQL backend and designed for stateless session management, this project demonstrates how to encrypt passwords, authenticate users, and protect endpoints with minimal configuration.

 🚀 Features
- ✅ User registration with BCrypt password hashing
- 🔐 Basic Authentication for protected endpoints
- 🧼 Stateless session management (`SessionCreationPolicy.STATELESS`)
- 🗃️ PostgreSQL integration via Spring Data JPA
- 🧠 Clean architecture with service, controller, and entity layers
- 🧪 Tested using Postman

🛠️ Tech Stack
- Java 17
- Spring Boot 3.5
- Spring Security
- Spring Data JPA
- PostgreSQL
- Lombok

 📂 Endpoints
| Method | Endpoint     | Description                     | Auth Required |
|--------|--------------|----------------------------------|---------------|
| POST   | `/register`  | Register a new user              | ❌ No          |
| GET    | `/greet`     | Greet user by username           | ✅ Yes         |

 ⚙️ Setup Instructions
1. Clone the repo
2. Create `application.properties` with your DB credentials
3. Run the app using `mvn spring-boot:run`
4. Test endpoints using Postman or curl

 📄 Sample `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

 👨‍💻 Author
**Sumit Madaan** — Backend Developer & Security Enthusiast  
 exploring scalable backend architectures, authentication flows, and system design.
