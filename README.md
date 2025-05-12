# Benjamin's E-Commerce Project

A simple Spring Boot e-commerce application developed as a class project.

## Project Overview

This is an e-commerce web application built with Spring Boot. The application provides basic functionality for users to browse products, add them to a cart, and place orders. It also includes an admin interface for managing products and orders.

## Technology Stack

- **Backend**: Spring Boot 3.2.3, Java 17
- **Database**: MySQL
- **Frontend**: Thymeleaf, Bootstrap (for responsive design)
- **Security**: Spring Security
- **Build Tool**: Maven

## Features Implemented

### 1. Project Initialization

- Setup basic Spring Boot project structure
- Added necessary dependencies (Spring Web, Data JPA, Security, Thymeleaf, MySQL, Validation, Lombok)
- Configured MySQL database connection

### 2. Database Configuration and Entity Models

- Configured MySQL database connection properties
- Created the following entities with proper relationships:
  - User: Basic user information and roles
  - Product: Product information including name, price, stock, and category
  - Order: Order details with status tracking
  - OrderItem: Individual items within an order

### 3. Security Configuration

- Implemented Spring Security for authentication and authorization
- Created role-based access control (ROLE_USER and ROLE_ADMIN)
- Setup secure password encoding with BCrypt
- Configured protected endpoints and public access areas
- Implemented user registration and authentication services

### 4. Repository and Service Layer

- Created repository interfaces using Spring Data JPA for User, Product, Order, and OrderItem
- Implemented service layer with business logic:
  - UserService: Registration and authentication functionality
  - ProductService: Product CRUD operations and search
  - OrderService: Order management with status tracking
  - CartService: Shopping cart functionality with session management

### 5. Controller and API Endpoints

- Created MVC controllers for different aspects of the application:
  - HomeController: Main pages and navigation
  - AuthController: User registration and login
  - ProductController: Product listings and details
  - CartController: Shopping cart operations
  - OrderController: Order placement and tracking
- Implemented role-based access control for admin functions
- Added form validation and error handling
- Used redirects with flash attributes for user feedback

### 6. Thymeleaf Frontend

- Created reusable layout template with navigation and footer
- Implemented responsive design using Bootstrap
- Developed key page templates:
  - Home page with featured products and categories
  - User authentication (login and registration)
  - Product listings and details
  - Shopping cart and checkout flow
  - Order management
- Added form validation with error messages
- Implemented security integration with Thymeleaf for conditional rendering

### 7. Additional Configuration and Error Handling

- Implemented global exception handling
- Created custom error pages
- Added sample data initialization for testing
- Configured proper logging

## Setup Instructions

### Prerequisites

- Java 17
- MySQL 8.0
- Maven

### Database Setup

1. Create a MySQL database named `ecommerce_db`
2. Update database credentials in `src/main/resources/application.properties` if needed

### Running the Application

```bash
# Clone the repository
git clone https://github.com/your-username/Benjamin-s-Project.git

# Navigate to the project directory
cd Benjamin-s-Project

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will be accessible at `http://localhost:8090`

### Default Users

The application initializes with two default users for testing:

1. Admin User

   - Username: admin
   - Password: password
   - Roles: ROLE_ADMIN, ROLE_USER

2. Regular User
   - Username: user
   - Password: password
   - Role: ROLE_USER

## Project Structure

```
src/main/java/com/benjamin/ecommerce/
├── config/               # Configuration classes
├── controller/           # MVC controllers
├── exception/            # Custom exceptions and handlers
├── model/                # Entity models
├── repository/           # Data repositories
├── security/             # Security configuration
├── service/              # Business logic
│   └── impl/             # Service implementations
└── EcommerceApplication.java  # Main application class

src/main/resources/
├── static/               # Static resources (CSS, JS)
├── templates/            # Thymeleaf templates
└── application.properties  # Application configuration
```

## Future Enhancements

- Add comprehensive unit and integration tests
- Implement pagination for product listings
- Add product image upload functionality
- Enhance user profile management
- Implement advanced search features
- Add payment gateway integration
- Optimize database queries for performance
- Implement product reviews and ratings
