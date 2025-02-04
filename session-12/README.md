# Composite Primary Key

## Overview

A composite primary key consists of more than one column in a table that together uniquely identify a record. In JPA, to
work with composite primary keys, we use two annotations:

- `@Embeddable`: Class-level annotation to represent the composite key columns.
- `@EmbeddedId`: Variable-level annotation to represent the embedded key class.

### Key Points:

- **Generators**: Generators (like `@GeneratedValue`) are not supported for composite primary keys.
- **Serializable**: The class representing the composite key must implement the `Serializable` interface.

### Example:

```java
import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeId implements Serializable {
    private Long departmentId;
    private Long employeeId;

    // Getters, Setters, equals, and hashCode methods
}
```

```java
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Employee {
    @EmbeddedId
    private EmployeeId id;

    private String name;
    private String role;

    // Getters and Setters
}
```

---

# Profiles in Spring Boot

## Overview

In real-world applications, multiple environments are used to test and deploy the application before delivering it to
the client. Each environment has its own configuration, including database credentials and URLs.

### Common Environments:

1. **Dev Environment (Development)**:
    - Used by developers for code integration and unit testing.
    - Example: `application-dev.properties`
2. **SIT Environment (System Integration Testing)**:
    - Used by testers for end-to-end testing of the application.
    - Example: `application-sit.properties`
3. **UAT Environment (User Acceptance Testing)**:
    - Used by the client or client-side team for acceptance testing.
    - **Go/No-Go Decision**: The client decides whether the application is ready for production.
        - **Go**: Approved for production deployment.
        - **No-Go**: Denied for production deployment.
    - Example: `application-uat.properties`
4. **PILOT Environment**:
    - Pre-production environment for testing with live data.
    - Example: `application-pilot.properties`
5. **PROD Environment (Production)**:
    - Live environment where end-users access the application.
    - Example: `application-prod.properties`

### Using Profiles in Spring Boot

Instead of manually changing the data source properties in the `application.properties` file, Spring Boot allows you to
define environment-specific properties files and activate them using profiles.

#### Example:

1. **Define Environment-Specific Properties Files**:
    - `application-dev.properties`:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/dev_db
      spring.datasource.username=dev_user
      spring.datasource.password=dev_password
      ```
    - `application-sit.properties`:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/sit_db
      spring.datasource.username=sit_user
      spring.datasource.password=sit_password
      ```
    - `application-prod.properties`:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/prod_db
      spring.datasource.username=prod_user
      spring.datasource.password=prod_password
      ```

2. **Activate a Profile**:
    - Use the `spring.profiles.active` property to activate a specific profile.
    - Example: Add the following line to `application.properties`:
      ```properties
      spring.profiles.active=dev
      ```
    - Alternatively, activate the profile at runtime using command-line arguments:
      ```bash
      java -jar myapp.jar --spring.profiles.active=sit
      ```

3. **Programmatic Profile Activation**:
    - You can also activate profiles programmatically in your application:
      ```java
      @SpringBootApplication
      public class MyApp {
          public static void main(String[] args) {
              SpringApplication app = new SpringApplication(MyApp.class);
              app.setAdditionalProfiles("uat");
              app.run(args);
          }
      }
      ```