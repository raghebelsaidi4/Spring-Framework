```markdown
# RESTful Services Guide

## Introduction to REST
- **REST (Representational State Transfer)**: A standard for building distributed applications enabling B2B (Business-to-Business) communication.
- **Distributed Applications**: Applications that reuse services from other applications via HTTP.
- **Interoperability**: Allows different systems to communicate using formats like XML/JSON.
- **Actors**:
  - **Provider**: Application offering business logic/services.
  - **Consumer**: Application consuming the provider's services.
  - One provider can serve multiple consumers.

## HTTP Protocol Basics
### HTTP Methods
| Method | Usage                      | Idempotent | Body  | Data Handling                     |
|--------|----------------------------|------------|-------|-----------------------------------|
| GET    | Retrieve data              | Yes        | No    | Uses path/query parameters in URL |
| POST   | Create a resource          | No         | Yes   | Sends data in request body        |
| PUT    | Update a resource          | Yes        | Yes   | Sends data in request body        |
| DELETE | Delete a resource          | Yes        | No    | Uses path/query parameters        |

### HTTP Status Codes
- **1xx**: Informational (e.g., 100 Continue)
- **2xx**: Success (e.g., 200 OK, 201 Created)
- **3xx**: Redirection (e.g., 301 Moved Permanently)
- **4xx**: Client Errors (e.g., 400 Bad Request, 404 Not Found)
- **5xx**: Server Errors (e.g., 500 Internal Server Error)

### HTTP Request Structure
1. **Request Line**: `HTTP_METHOD URL` (e.g., `GET /api/users`)
2. **Headers**: Key-value pairs (e.g., `Content-Type: application/json`)
3. **Blank Line**: Separates headers and body.
4. **Body**: Data payload (for POST/PUT).

---

## Data Formats
### XML (Extensible Markup Language)
- Represents data in tags. Platform/language-independent.
- **Example**:
  ```xml
  <student id="101">
    <name>Raj</name>
    <age>25</age>
  </student>
  ```
- **JAX-B (Java API for XML Binding)**:
    - Converts Java objects ↔ XML (**Marshalling/Unmarshalling**).
    - Requires binding classes with JAX-B annotations (e.g., `@XmlRootElement`).

### JSON (JavaScript Object Notation)
- Lightweight key-value format. Preferred for B2B.
- **Example**:
  ```json
  {
    "name": "Raj",
    "age": 25
  }
  ```
- **Jackson API**:
    - Converts Java objects ↔ JSON (**Serialization/Deserialization**).
    - Uses `ObjectMapper` class.

---

## Developing REST APIs with Spring Boot
### Step 1: Setup Dependencies
Include in `pom.xml`:
```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
  </dependency>
</dependencies>
```

### Step 2: Create a RestController
```java
@RestController
@RequestMapping("/api")
public class UserController {

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    User user = userService.findById(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User savedUser = userService.save(user);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }
}
```

### Step 3: Test with Postman
- **GET Request**: `http://localhost:8080/api/users/101`
- **POST Request**: Send JSON body to `http://localhost:8080/api/users`.

---

## Best Practices
- Use JSON for data exchange (lightweight, easy parsing).
- Follow REST naming conventions (e.g., plural nouns for endpoints: `/api/users`).
- Handle exceptions using `@ControllerAdvice`.
- Secure APIs with Spring Security.

## Conclusion
RESTful services enable seamless communication between distributed systems. By leveraging HTTP methods, status codes, and data formats like JSON, developers can build scalable and interoperable APIs. Spring Boot simplifies REST API development with annotations and auto-configuration.
