```markdown
# RESTful Services: Advanced Concepts

## Handling Request Parameters

### Query Parameters
- **Usage**: Send optional data as key-value pairs in the URL (e.g., filtering, sorting).
- **Syntax**: Starts with `?`, separated by `&`.
EX: GET /api/users?role=admin&page=2
  ```
- **Spring Annotation**: `@RequestParam`
  ```java
  @GetMapping("/users")
  public List<User> getUsers(@RequestParam String role, @RequestParam int page) { ... }
  ```

### Path Parameters (URL Variables)
- **Usage**: Identify specific resources in the URL path.
- **Syntax**: Defined in URL templates, separated by `/`.
  
- **Spring Annotation**: `@PathVariable`
  ```java
  @GetMapping("/users/{id}")
  public User getUser(@PathVariable Long id) { ... }
  ```

| **Parameter Type** | Use Case                   | Example URL              | Annotation       |
|---------------------|---------------------------|--------------------------|------------------|
| Query Param         | Filtering/Sorting         | `/users?role=admin`      | `@RequestParam`  |
| Path Param          | Unique Resource Access    | `/users/101`             | `@PathVariable`  |

---

## Content Negotiation: `consumes` and `produces`
- **`produces`**: Specifies response format(s) (e.g., JSON/XML).  
  Client sets `Accept` header to request a format.
  ```java
  @GetMapping(value = "/users/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public User getUser(...) { ... }
  ```
- **`consumes`**: Restricts the request body format the method accepts.  
  Client sets `Content-Type` header.
  ```java
  @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
  public User createUser(...) { ... }
  ```

---

## HTTP Methods in Depth

### POST (`@PostMapping`)
- **Purpose**: Create a new resource.
- **Request Body**: Required (use `@RequestBody`).
  ```java
  @PostMapping("/users")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User savedUser = service.save(user);
    return ResponseEntity.created(URI.create("/users/" + savedUser.getId()))
                         .body(savedUser);
  }
  ```

### PUT (`@PutMapping`)
- **Purpose**: Update an entire resource (idempotent).
- **Example**:
  ```java
  @PutMapping("/users/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody User user) {
    return service.update(id, user);
  }
  ```

### DELETE (`@DeleteMapping`)
- **Purpose**: Delete a resource.
  ```java
  @DeleteMapping("/users/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
  ```

---

## Message Converters & `ResponseEntity`
- **Message Converters**: Automatically convert request/response bodies between formats (JSON ↔ Java objects) using libraries like Jackson.
- **`ResponseEntity`**: Customize HTTP responses (status code, headers, body).
  ```java
  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUser(...) {
    User user = service.findById(id);
    return user != null 
        ? ResponseEntity.ok(user) 
        : ResponseEntity.notFound().build();
  }
  ```

---

## API Documentation with Swagger
**Swagger (OpenAPI)** automates REST API documentation and provides a testing UI.

### Integration Steps:
1. **Add Dependency** (SpringDoc):
   ```xml
   <dependency>
     <groupId>org.springdoc</groupId>
     <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
     <version>2.1.0</version>
   </dependency>
   ```
2. **Access Swagger UI**:  
   Visit `http://localhost:8080/swagger-ui.html`.

### Annotate APIs:
```java
@Operation(summary = "Get a user by ID")
@ApiResponse(responseCode = "200", description = "User found")
@GetMapping("/users/{id}")
public User getUser(...) { ... }
```
