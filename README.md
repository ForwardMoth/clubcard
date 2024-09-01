# Club card service 

## Features

- Registration users
- Login
- Get user info and qr-code, managing user profile
- Manage card types and privileges
- Creating plastic cards and managing card requests

## Stack

- Java 21
- Spring Boot 3
- Spring Security 6
- PostgreSQL 14
- Hibernate 6.5.2 
- Liquibase 4.22
- Maven 4.0.0
- Swagger 3.0
- Docker 27.1.1
- Docker Compose 2.29.1

## Get Started

1. Setup configuration .env

```env
DB_USER=DB_USER
DB_PASSWORD=DB_PASSWORD
DB_NAME=clubcard
DB_HOST=clubcard
DB_PORT=5432

ACCESS_SECRET_KEY=ACCESS_SECRET_KEY
ACCESS_LIFETIME=5m

REFRESH_SECRET_KEY=REFRESH_SECRET_KEY
REFRESH_LIFETIME=1d

HTTP_PORT=8080
```

2. Run

```bash
docker compose up --build 
```

## Swagger documentation

http://localhost:8080/api/swagger-ui/index.html