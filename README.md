# Agenda Spring Boot Application

A RESTful API for user authentication and contact management built with Spring Boot, Maven, and H2 database.

## Features

### Authentication
- **User Registration**: `POST /api/auth/signup`
- **User Login**: `POST /api/auth/login`

### Contact Management (CRUD)
- **Create Contact**: `POST /api/agenda`
- **List All Contacts**: `GET /api/agenda`
- **Get Contact by ID**: `GET /api/agenda/{id}`
- **Update Contact**: `PUT /api/agenda/{id}`
- **Delete Contact**: `DELETE /api/agenda/{id}`

## Technology Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Boot Starter Web**
- **Spring Boot Starter Data JPA**
- **Spring Boot DevTools**
- **Spring Boot Actuator**
- **H2 Database** (in-memory)
- **Maven** (build tool)

## Project Structure

```
src/
├── main/
│   ├── java/com/example/agenda/
│   │   ├── controller/          # REST Controllers
│   │   ├── dto/                 # Data Transfer Objects
│   │   ├── model/               # JPA Entities
│   │   ├── repository/          # JPA Repositories
│   │   ├── service/             # Business Logic
│   │   └── AgendaApplication.java
│   └── resources/
│       ├── application.properties
│       └── application-prod.properties
```

## API Endpoints

### Authentication

#### Register User
```http
POST /api/auth/signup
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao@example.com",
  "senha": "senha123"
}
```

#### Login User
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "joao@example.com",
  "senha": "senha123"
}
```

### Contact Management

#### Create Contact
```http
POST /api/agenda
Content-Type: application/json

{
  "nome": "Maria Silva",
  "telefone": "+5511987654321"
}
```

#### Get All Contacts
```http
GET /api/agenda
```

#### Get Contact by ID
```http
GET /api/agenda/1
```

#### Update Contact
```http
PUT /api/agenda/1
Content-Type: application/json

{
  "nome": "Maria Silva Atualizada",
  "telefone": "+5511123456789"
}
```

#### Delete Contact
```http
DELETE /api/agenda/1
```

## Running the Application

### Local Development

1. **Prerequisites**: Java 17, Maven (or use included Maven wrapper)

2. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access the application**:
   - API: http://localhost:8080
   - H2 Console: http://localhost:8080/h2-console
   - Health Check: http://localhost:8080/actuator/health

### Docker Deployment

#### Build and Run with Docker Compose

1. **Build and start the application**:
   ```bash
   docker-compose up --build
   ```

2. **Run in detached mode**:
   ```bash
   docker-compose up -d --build
   ```

3. **Stop the application**:
   ```bash
   docker-compose down
   ```

#### Manual Docker Commands

1. **Build the Docker image**:
   ```bash
   docker build -t agenda-app .
   ```

2. **Run the container**:
   ```bash
   docker run -p 8080:8080 agenda-app
   ```

### VPS Deployment

1. **Copy files to your VPS**:
   ```bash
   scp -r . user@your-vps-ip:/path/to/deployment/
   ```

2. **SSH into your VPS**:
   ```bash
   ssh user@your-vps-ip
   ```

3. **Navigate to deployment directory**:
   ```bash
   cd /path/to/deployment/
   ```

4. **Run with Docker Compose**:
   ```bash
   docker-compose up -d --build
   ```

5. **Check logs**:
   ```bash
   docker-compose logs -f
   ```

## Configuration

### Development
- Uses `application.properties`
- H2 console enabled
- SQL logging enabled

### Production
- Uses `application-prod.properties`
- H2 console disabled
- Reduced logging
- Health check endpoints enabled

## Database

The application uses H2 in-memory database by default. For production, consider switching to:
- PostgreSQL
- MySQL
- MariaDB

## Health Checks

The application includes Spring Boot Actuator for monitoring:
- Health endpoint: `/actuator/health`

## Security Notes

⚠️ **Important**: This is a basic implementation for demonstration purposes. For production use, consider:

1. **Password Hashing**: Implement proper password hashing (BCrypt)
2. **JWT Authentication**: Add JWT tokens for stateless authentication
3. **Input Validation**: Add comprehensive input validation
4. **HTTPS**: Use HTTPS in production
5. **Database**: Use a persistent database instead of H2
6. **Error Handling**: Implement global exception handling
7. **Rate Limiting**: Add rate limiting for API endpoints

## Testing

Run tests with:
```bash
./mvnw test
```

## License

This project is for educational purposes.
