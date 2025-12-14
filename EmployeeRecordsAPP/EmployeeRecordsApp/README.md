# Employee Records (Java Swing CRUD)

**Group Members:**
- UWINEZA Clementine Amina — 24RP03455
- KUBAHONIYEZU Theopiste — 24RP01459

**Project Title:** Employee Records Management - Java Swing + JDBC

## Short Description
A simple Java Swing desktop application that manages employee records using SQLite and JDBC. The app demonstrates CRUD operations, input validation (RegEx), PreparedStatements, and a JTable UI.

## Features
- Add new employee (name, email, position, salary)
- View employees in a JTable
- Update selected employee
- Delete selected employee
- Input validation using RegEx for email and numeric salary

## Tech stack
- Java 11
- Java Swing for UI
- SQLite (via JDBC)
- Maven build

## Files included
- `src/` - Java source code (packages: `model`, `db`, `service`, `ui`)
- `sql/schema.sql` - SQL file to create database table
- `pom.xml` - Maven build file

## How to run (Windows)
1. Ensure Java 11+ and Maven are installed.
2. Open a terminal in the `EmployeeRecordsApp` folder.
3. Build the jar with dependencies:

```bash
mvn clean package
```

4. Run the application (jar-with-dependencies is generated):

```bash
java -jar target/EmployeeRecordsApp-1.0.0-jar-with-dependencies.jar
```

The app creates `employees.db` in the project folder on first run.

## Database
You can inspect or initialize the DB manually using the `sql/schema.sql` file with an SQLite client.

## Screenshots
- (Add screenshots here after running the app)

## Notes for submission
- This repo is ready to fork and push to your GitHub account. Replace screenshots in `README.md` with actual images before final submission.
