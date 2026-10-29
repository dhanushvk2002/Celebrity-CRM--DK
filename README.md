# Celebrity CRM Backend

A Spring Boot enterprise backend application designed for managing celebrities, talent agencies, contracts, campaigns, OTT streaming projects, scheduling calendars, casting calls, files, and executive analytics.

---

## 🛠️ Tech Stack

- **Java**: 17
- **Framework**: Spring Boot 4 / Spring Framework 7
- **Database**: MySQL 8.0+
- **Security**: Spring Security 6 with JWT (JSON Web Tokens)
- **ORM / Persistence**: Spring Data JPA / Hibernate ORM 7
- **Database Migrations**: Flyway
- **API Documentation**: SpringDoc OpenAPI / Swagger UI (OpenAPI 3)
- **Utilities**: Lombok, Jakarta Validation

---

## 📁 Project Architecture & File Structure

```text
celebrity-crm-backend/
│
├── pom.xml
├── .gitignore
├── README.md
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── celebritycrm/
│   │   │           │
│   │   │           ├── CelebrityCrmApplication.java
│   │   │           │
│   │   │           ├── config/
│   │   │           │   ├── SecurityConfig.java
│   │   │           │   ├── CorsConfig.java
│   │   │           │   ├── JpaConfig.java
│   │   │           │   └── OpenApiConfig.java
│   │   │           │
│   │   │           ├── security/
│   │   │           │   ├── AuthController.java
│   │   │           │   ├── AuthService.java
│   │   │           │   ├── JwtService.java
│   │   │           │   ├── JwtAuthenticationFilter.java
│   │   │           │   ├── CustomUserDetailsService.java
│   │   │           │   └── dto/
│   │   │           │       ├── RegisterRequest.java
│   │   │           │       ├── LoginRequest.java
│   │   │           │       └── AuthResponse.java
│   │   │           │
│   │   │           ├── user/
│   │   │           │   ├── User.java
│   │   │           │   └── UserRepository.java
│   │   │           │
│   │   │           ├── common/
│   │   │           │   ├── exception/
│   │   │           │   │   ├── GlobalExceptionHandler.java
│   │   │           │   │   ├── ResourceNotFoundException.java
│   │   │           │   │   └── BusinessException.java
│   │   │           │   ├── response/
│   │   │           │   │   └── ApiResponse.java
│   │   │           │   ├── enums/
│   │   │           │   ├── audit/BaseEntity.java
│   │   │           │   └── util/SecurityUtils.java
│   │   │           │
│   │   │           ├── dashboard/
│   │   │           │   ├── controller/DashboardController.java
│   │   │           │   ├── service/DashboardService.java
│   │   │           │   ├── repository/DashboardRepository.java
│   │   │           │   └── dto/
│   │   │           │       ├── DashboardSummaryDto.java
│   │   │           │       └── AnalyticsDto.java
│   │   │           │
│   │   │           ├── celebrity/
│   │   │           │   ├── controller/CelebrityController.java
│   │   │           │   ├── service/
│   │   │           │   │   ├── CelebrityService.java
│   │   │           │   │   └── CelebrityServiceImpl.java
│   │   │           │   ├── repository/CelebrityRepository.java
│   │   │           │   ├── entity/Celebrity.java
│   │   │           │   ├── dto/
│   │   │           │   └── mapper/CelebrityMapper.java
│   │   │           │
│   │   │           ├── management/
│   │   │           │   ├── manager/
│   │   │           │   └── agency/
│   │   │           │
│   │   │           ├── contracts/
│   │   │           ├── campaigns/
│   │   │           ├── scheduling/
│   │   │           ├── ott/
│   │   │           ├── casting/
│   │   │           ├── files/
│   │   │           └── notifications/
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       ├── application-prod.properties
│   │       └── db/
│   │           └── migration/
│   │               ├── V1__create_users_and_roles.sql
│   │               ├── V2__create_celebrities.sql
│   │               ├── V3__create_managers_and_agencies.sql
│   │               ├── V4__create_contracts.sql
│   │               ├── V5__create_campaigns.sql
│   │               ├── V6__create_scheduling.sql
│   │               └── V7__create_ott_and_casting.sql
│   │
│   └── test/
│       └── java/
│           └── com/celebritycrm/
│               ├── CelebrityCrmApplicationTests.java
│               ├── celebrity/CelebrityServiceTest.java
│               ├── contracts/ContractServiceTest.java
│               ├── campaigns/CampaignServiceTest.java
│               └── scheduling/ScheduleServiceTest.java
```

---

## 🚀 Getting Started

### 1. Prerequisites
- Java 17+
- Maven 3.9+
- MySQL Server (running on `localhost:3306`)

### 2. Configure Database
In `src/main/resources/application-dev.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/celebrity_crm?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
```

### 3. Build & Run
```bash
# Compile and build
mvn clean install

# Run the application
mvn spring-boot:run
```

---

## 📑 API Endpoints Summary

| Module | Endpoint | Methods | Description |
|---|---|---|---|
| **Auth** | `/api/auth/register`, `/api/auth/login` | `POST` | User registration & JWT authentication |
| **Dashboard** | `/api/dashboard/summary`, `/api/dashboard/analytics` | `GET` | KPIs, summaries, and distribution analytics |
| **Celebrities** | `/api/celebrities` | `GET, POST, PUT, DELETE` | Talent profiles and management |
| **Agencies** | `/api/management/agencies` | `GET, POST, PUT, DELETE` | Agency representation |
| **Managers** | `/api/management/managers` | `GET, POST, PUT, DELETE` | Manager accounts & assignments |
| **Contracts** | `/api/contracts` | `GET, POST, PUT, PATCH, DELETE` | Brand endorsement & talent contracts |
| **Campaigns** | `/api/campaigns` | `GET, POST, PUT, PATCH, DELETE` | Marketing & ad campaign deals |
| **Scheduling** | `/api/scheduling` | `GET, POST, PUT, PATCH, DELETE` | Appearance & calendar scheduling |
| **OTT Projects**| `/api/ott` | `GET, POST, PUT, PATCH, DELETE` | Web series, streaming & film projects |
| **Casting** | `/api/casting` | `GET, POST, PUT, PATCH, DELETE` | Auditions & casting calls |
| **Files** | `/api/files/upload`, `/api/files/download/{file}` | `POST, GET` | Media & document handling |

---

## 📖 Swagger / OpenAPI Documentation
Once running, browse to:
```
http://localhost:8080/swagger-ui/index.html
```

