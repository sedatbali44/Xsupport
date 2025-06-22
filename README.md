# Xsupport
# Xsupport - Ticket Management System

A comprehensive REST API service for ticket management built with Spring Boot 3.5.3. This application provides secure authentication, ticket lifecycle management, and user administration capabilities for support systems.

## Overview

Xsupport is a modern ticket management system designed to streamline customer support operations. The application offers JWT-based authentication, role-based access control, and comprehensive ticket tracking capabilities. Built with enterprise-grade technologies, it provides a robust foundation for customer support workflows.

## Technology Stack

The application leverages the following technologies and frameworks:

**Backend Framework:** Spring Boot 3.5.3 with Java 24
**Database:** PostgreSQL with Spring Data JPA and Hibernate
**Security:** Spring Security with JWT authentication
**Documentation:** OpenAPI 3 with Swagger UI
**Build Tool:** Apache Maven
**Additional Libraries:** Lombok for code generation, Spring Session for session management

## Features

**Authentication & Authorization**
- User registration and login with JWT token-based authentication
- Secure password handling and session management
- Role-based access control for different user types

**Ticket Management**
- Create, update, and track support tickets
- Advanced search and filtering capabilities with pagination
- User-specific ticket views and management
- Comprehensive ticket lifecycle tracking

**User Management**
- User profile management and updates
- Administrative user oversight capabilities
- Username-based user lookup functionality

**API Documentation**
- Interactive Swagger UI for API exploration
- Comprehensive OpenAPI 3 documentation
- Clear endpoint documentation with request/response examples

## Prerequisites

Before running the application, ensure you have the following installed:

- Java Development Kit (JDK) 24 or later
- Apache Maven 3.6 or later
- PostgreSQL 12 or later
- Git for version control

## Installation & Setup

**Database Configuration**

Create a PostgreSQL database and update the connection details in `application.yaml`. The default configuration expects a PostgreSQL instance running on localhost:5432 with a database named 'postgres'. Ensure your PostgreSQL user has appropriate permissions for schema creation and data manipulation.

**Environment Setup**

Clone the repository and navigate to the project directory. The application uses Maven for dependency management, so ensure Maven is properly configured in your development environment.

**Application Configuration**

Review and update the `application.yaml` file according to your environment requirements. Key configuration areas include database connection parameters, JWT secret key configuration, and logging levels. For production deployments, ensure you update the JWT secret key and database credentials appropriately.

## Running the Application

**Development Mode**

Execute the following Maven command to start the application in development mode:

```bash
mvn spring-boot:run
```

The application will start on port 8080 with the base path `/api/v1`. All API endpoints will be accessible under this context path.

**Production Build**

To create a production-ready JAR file, use the following command:

```bash
mvn clean package
java -jar target/Xsupport-0.0.1-SNAPSHOT.jar
```

## API Documentation

Once the application is running, access the interactive API documentation through Swagger UI at:

```
http://localhost:8080/api/v1/swagger-ui.html
```

The OpenAPI specification is available at:

```
http://localhost:8080/api/v1/api-docs
```

## API Endpoints

**Authentication Endpoints**
- `POST /api/v1/auth/sign-up` - User registration
- `POST /api/v1/auth/sign-in` - User authentication

**Ticket Management Endpoints**
- `POST /api/v1/ticket/create` - Create new support ticket
- `PUT /api/v1/ticket/update` - Update existing ticket
- `GET /api/v1/ticket/all` - Retrieve tickets with filtering and pagination
- `GET /api/v1/ticket/own-tickets` - Get current user's tickets

**User Management Endpoints**
- `GET /api/v1/user/me` - Get current user profile
- `GET /api/v1/user/all-users` - Retrieve all users (admin)
- `GET /api/v1/user/find-byname` - Find user by username
- `PUT /api/v1/user/update` - Update user profile

