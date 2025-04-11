# Agrotis Challenge API

This project is an API developed to meet the requirements of the Agrotis challenge. It provides endpoints for managing person and related entities.

## Features
- CRUD operations for person, properties and laboratories.
- Integration with PostgreSQL database.
- Database versioning and migrations using Flyway.
- Built with Spring Boot for rapid development and scalability.

## Technologies Used
- **Java**: 23.0.2
- **Spring Boot**: 3.4.4
- **PostgreSQL**: Latest (Docker)
- **Flyway**: 10.20.1
- **Gradle**: 8.x
- **Postman**: For API testing and documentation.

## Prerequisites
- Docker and Docker Compose installed on your machine.
- Postman (optional, for testing the API).

## How to Run the Project

1. **Clone the Repository**

2. **Navigate to the Project Directory**:
   ```bash
   cd agrotis-challenge
   ```

3. **Build and Run the Application with Docker**:
   ```bash
   docker-compose up --build
   ```
4. **Access the Application**:
   - The application will be available at `http://localhost:8080`.
