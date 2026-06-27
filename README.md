# Business Dashboard

A full-stack Business Dashboard application built with React, Spring Boot, PostgreSQL, JWT Authentication, and Bootstrap.

## Features

* User Registration
* User Login with JWT Authentication
* Protected Dashboard
* CSV File Upload
* CSV Validation
* User-Specific Data Isolation
* Search Uploaded Files
* Delete Uploaded Files
* Dashboard Summary Cards
* Category Summary Table
* Bar Chart Visualization
* Pie Chart Visualization
* Logout Functionality
* Global Error Handling

## Technology Stack

### Frontend

* React
* React Router
* Axios
* Bootstrap
* Recharts

### Backend

* Spring Boot
* Spring Security
* Spring Data JPA
* JWT Authentication

### Database

* PostgreSQL

---

## Database Setup

Create a PostgreSQL database:

```sql
CREATE DATABASE dashboard;
```

Create a PostgreSQL user:

```sql
CREATE USER dashboard_user WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE dashboard TO dashboard_user;
```

---

## Backend Configuration

Update `application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/dashboard
    username: dashboard_user
    password: password
```

---

## Running the Backend

Navigate to the backend project:

```bash
cd dashboard
```

Run:

```bash
mvn spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

---

## Running the Frontend

Navigate to the frontend:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start React:

```bash
npm start
```

Frontend runs on:

```text
http://localhost:3000
```

---

## Test Accounts

Create accounts through the Register page.

Example:

Email:

```text
hamza@test.com
```

Password:

```text
password123
```

---

## CSV Format

Example valid CSV:

```csv
Food,2026-01-01,100
Transport,2026-01-02,200
Entertainment,2026-01-03,150
```

---

## Validation Rules

* File must be CSV
* Empty files rejected
* Blank lines ignored
* Missing columns rejected
* Extra columns rejected
* Invalid numeric values rejected

---

## Security Features

* Password hashing using BCrypt
* JWT Authentication
* Protected Dashboard Routes
* User-specific data access
* Upload ownership verification

---

## Author

Hamza Erfan

BSc Computer Science

Royal Holloway, University of London

