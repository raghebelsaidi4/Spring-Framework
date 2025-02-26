# CRUD Operations using REST API with MySQL Database

## Developing REST API using Layered Architecture with MySQL Database
This example demonstrates how to develop a RESTful API using a three-layered architecture with MySQL as the database.

### Layered Architecture:
1. **Web Layer**: Handles incoming HTTP requests and responses.
2. **Business/Service Layer**: Contains the business logic.
3. **DAO (Data Access Object) Layer**: Manages database operations.

## Technologies Used:
- **Spring Boot**
- **Spring Data JPA**
- **MySQL Database**
- **Hibernate**
- **Maven**
- **IntelliJ IDEA**

---

## Project Structure
```
rest-api-mysql
│── src/main/java/com/example
│   ├── controller
│   │   ├── EmployeeController.java
│   ├── service
│   │   ├── EmployeeService.java
│   │   ├── EmployeeServiceImpl.java
│   ├── repository
│   │   ├── EmployeeRepository.java
│   ├── model
│   │   ├── Employee.java
│   ├── exception
│   │   ├── ResourceNotFoundException.java
│   ├── RestApiMysqlApplication.java
│── src/main/resources
│   ├── application.properties
│── pom.xml
```

## 1. Define the Model
```java
package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String department;

    // Getters and Setters
}
```

## 2. Create the Repository
```java
package com.example.repository;

import com.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
```

## 3. Implement the Service Layer
```java
package com.example.service;

import com.example.model.Employee;
import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
}
```

### Service Implementation
```java
package com.example.service;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(employeeDetails.getDepartment());
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}
```

## 4. Create the Controller Layer
```java
package com.example.controller;

import com.example.model.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
```

## 5. Configure MySQL Database in `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employees_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## Testing API using Postman
- **Create Employee**: `POST http://localhost:8080/api/employees`
- **Get All Employees**: `GET http://localhost:8080/api/employees`
- **Get Employee by ID**: `GET http://localhost:8080/api/employees/{id}`
- **Update Employee**: `PUT http://localhost:8080/api/employees/{id}`
- **Delete Employee**: `DELETE http://localhost:8080/api/employees/{id}`

---

# Embedded Database (H2)

### What is an Embedded Database?
An embedded database is a temporary in-memory database primarily used for **Proof of Concept (POC)** development. It does not require installation and comes bundled with the application as a dependency.

### Features:
- Automatically created when the application starts.
- Data is **not persistent** (lost when the application stops).
- Used for development and testing environments (not suitable for SIT, UAT, or PROD).

### How to Use H2 Database in Spring Boot?
#### Add Dependency in `pom.xml`
```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

#### Configure H2 Database in `application.properties`
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

#### Access H2 Console
URL: `http://localhost:8080/h2-console`