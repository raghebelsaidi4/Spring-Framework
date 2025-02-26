# Exception Handling in REST API (Spring Boot)

## Introduction
Exception handling is a crucial aspect of building a robust REST API. An exception is an unexpected and unwanted situation that occurs in an application, potentially leading to abnormal termination of the program. To ensure a graceful termination and provide meaningful error responses to clients, we must handle exceptions properly.

## Exception Handling Mechanisms in Java
Java provides several mechanisms for handling exceptions:
- **try** – Defines a block of code in which an exception might occur.
- **catch** – Defines a block of code that handles an exception.
- **finally** – A block that always executes, regardless of whether an exception occurred.
- **throw** – Used to explicitly throw an exception.
- **throws** – Declares exceptions that a method might throw.

## Exception Handling in REST APIs
When an exception occurs in a REST API, it should be conveyed to the client in JSON format. For example:

```json
{
    "message": "User not found",
    "code": 404
}
```

In a project, every exception should have a corresponding **exception code** to standardize error responses.

## Approaches for Exception Handling in Spring Boot
Spring provides two main ways to handle exceptions:

### 1. Controller-Based Exception Handling
Exception handlers are applicable only to a specific controller.

#### Example:
```java
@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return ResponseEntity.ok(user);
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("code", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
```

### 2. Global Exception Handling
Global exception handlers are applicable to all controllers.

#### Example:
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("code", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "An unexpected error occurred.");
        response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

## Custom Exception Class
To define a custom exception:
```java
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
```

## Benefits of Exception Handling in Spring Boot
- Provides meaningful error responses to the client.
- Standardizes error messages across the application.
- Enhances debugging and logging.
- Improves maintainability and code cleanliness.

## Conclusion
By implementing controller-based or global exception handling, we can build robust Spring Boot REST APIs that gracefully handle errors and provide clear responses to clients.

