# JWT Authentication Backend – Spring Boot

A backend authentication system built using **Java**, **Spring Boot**, and **Spring Security** that implements **JWT-based authentication**.

This project demonstrates how stateless authentication works in modern backend systems using JSON Web Tokens (JWT), along with secure password handling and protected endpoints.

---

## Features

- User registration with encrypted passwords
- User login with JWT token generation
- Stateless authentication using JWT
- Custom JWT filter for request validation
- Protected API endpoints
- Spring Security integration
- Clean separation of concerns (Controller, Service, Repository)

---

## Tech Stack

- **Java**
- **Spring Boot**
- **Spring Security**
- **JWT (io.jsonwebtoken)**
- **Spring Data JPA**
- **Hibernate**
- **BCrypt Password Encoder**
- **Maven**
- **H2 / MySQL / PostgreSQL** (any JPA-supported DB)

---

## Project Structure

```
src/
├── main/
│   ├── java/com/practice/spring_security/
│   │   ├── controller/
│   │   │   └── UserController.java
│   │   ├── filter/
│   │   │   └── JwtFilter.java
│   │   ├── model/
│   │   │   ├── Userss.java
│   │   │   └── UserPrinciple.java
│   │   ├── repo/
│   │   │   └── UsersRepo.java
│   │   ├── securityConfig/
│   │   │   └── SecurityConfig.java
│   │   ├── service/
│   │   │   ├── JwtService.java
│   │   │   └── UserService.java
│   │   └── SpringSecurityApplication.java
│   └── resources/
│       └── application.properties
```

---

## API Endpoints

### 1 Register User
```
POST /register
```

**Request Body**
```json
{
  "username": "rohit",
  "password": "password123",
  "name": "Rohit Kumar"
}
```

---

### 2 Login User
```
POST /login
```

**Request Body**
```json
{
  "username": "rohit",
  "password": "password123"
}
```

**Response**
```
JWT Token (String)
```

---

### 3 Protected Endpoint
```
GET /greet
```

**Header**
```
Authorization: Bearer <JWT_TOKEN>
```

**Response**
```
Hello <Name> Welcome to Spring Security
```

---

## How Authentication Works

1. User logs in using username & password
2. Spring Security authenticates credentials
3. JWT token is generated and returned
4. Client sends JWT in `Authorization` header
5. `JwtFilter` validates the token for each request
6. Security context is set if token is valid
7. Protected endpoints are accessible

---

## ⚠️ Notes

- JWT secret key is **generated at runtime** (for learning purposes)
- Token expiration is currently **3 minutes**
- No refresh token implemented (can be added later)
- Role-based authorization can be extended easily

---

## How to Run Locally

1. Clone the repository
   ```
   git clone https://github.com/STONE-ROHIT/spring-boot-jwt-auth.git
   ```

2. Navigate to the project
   ```
   cd spring-boot-jwt-auth
   ```

3. Run the application
   ```
   mvn spring-boot:run
   ```

4. Test APIs using **Postman**

---

## Future Improvements

- Refresh tokens
- Role-based authorization (ADMIN / USER)
- Global exception handling
- DTOs for request/response
- API documentation using Swagger

---

## Author

**Rohit Kumar**  
Backend Developer | Java & Spring Boot  
GitHub: https://github.com/STONE-ROHIT
