# BJJ Copilot Backend

Spring Boot-based backend API for the BJJ Copilot system.

## Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL 15+ (for production)
- Redis 7+ (for production)

## Setup

### Development Environment

```bash
# Compile the application
mvn clean compile

# Run tests
mvn test

# Start the application (development mode)
mvn spring-boot:run

# The API will be available at http://localhost:8080
```

### Production Environment

Configure the following environment variables:

```bash
DB_USERNAME=your_postgres_username
DB_PASSWORD=your_postgres_password
REDIS_PASSWORD=your_redis_password
JWT_SECRET=your-jwt-secret-key
```

## Available Maven Goals

- `mvn clean compile` - Compile the source code
- `mvn test` - Run unit tests
- `mvn spring-boot:run` - Start the application in development mode
- `mvn clean package` - Build the JAR file
- `mvn clean install` - Install to local Maven repository

## Architecture

This Spring Boot application follows the architecture defined in the main [ARCHITECTURE.md](../ARCHITECTURE.md) document and includes:

- **Spring Boot 3.2.1** with Java 17
- **Spring Security** for authentication and authorization
- **Spring Data JPA** for data persistence
- **Spring Web** for REST APIs
- **PostgreSQL** for database
- **Redis** for caching and sessions
- **Spring Boot Actuator** for monitoring

## API Endpoints

### Health Check
- `GET /api/health` - Application health status

### Authentication (Future)
- `POST /api/auth/login` - User login
- `POST /api/auth/logout` - User logout
- `GET /api/auth/me` - Current user info

### Users (Future)
- `GET /api/users` - List users
- `POST /api/users` - Create user
- `GET /api/users/{id}` - Get user by ID

### Graduations (Future)
- `GET /api/graduations` - List graduations
- `POST /api/graduations` - Create graduation record

## Testing

The application uses:
- **JUnit 5** for unit testing
- **Spring Boot Test** for integration testing
- **H2 Database** for test data
- **Test Profile** with disabled security for easier testing

## Configuration

### Development (application.properties)
- PostgreSQL on localhost:5432
- Redis on localhost:6379
- JWT secret (change in production)
- Debug logging enabled

### Test (application-test.properties)
- H2 in-memory database
- Disabled Redis auto-configuration
- Disabled Spring Security
- Reduced logging

## Security

- JWT-based authentication
- Role-based access control
- Spring Security integration
- Password encryption (planned)

## Monitoring

Spring Boot Actuator endpoints available:
- `/actuator/health` - Health check
- `/actuator/info` - Application info
- `/actuator/metrics` - Application metrics