# REST Client in Spring Boot

A **REST Client** is an application that accesses a **REST API**. It communicates with the API over the **HTTP protocol** to send requests and receive responses.

Spring Boot provides built-in support to develop REST clients in Java.

## Ways to Develop a REST Client in Spring Boot

There are two main approaches in Spring Boot to develop a REST client:

### 1. `RestTemplate` (Deprecated)
- **Introduced before Spring Boot 5.x**
- **Synchronous communication only** (blocking the thread until a response is received)
- **Dependency:** `spring-boot-starter-webflux`
- **Usage Example:**

```java
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

public class RestTemplateExample {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        System.out.println("Response: " + response.getBody());
    }
}
```

### 2. `WebClient` (Recommended)
- **Introduced in Spring Boot 5.x**
- **Supports both synchronous and asynchronous communication**
- **Dependency:** `spring-boot-starter-web`
- **Usage Example:**

#### Synchronous WebClient Example:
```java
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientExample {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");
        
        String response = webClient.get()
                .uri("/posts/1")
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Synchronous
        
        System.out.println("Response: " + response);
    }
}
```

#### Asynchronous WebClient Example:
```java
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientAsyncExample {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");
        
        Mono<String> responseMono = webClient.get()
                .uri("/posts/1")
                .retrieve()
                .bodyToMono(String.class);
        
        responseMono.subscribe(response -> System.out.println("Response: " + response)); // Asynchronous
    }
}
```

## Synchronous vs Asynchronous Communication
- **Synchronous:** Blocks the thread until a response is received.
- **Asynchronous:** Does not block the thread and allows other tasks to execute while waiting for the response.

---

## How to Develop a REST Client
To develop a REST client, you need to know the following details about the REST API:
1. **API URL**
2. **API Request Method** (GET, POST, PUT, DELETE, etc.)
3. **API Request Data Structure** (if applicable)
4. **API Response Data Structure**

Example REST API details:
- **URL:** `https://jsonplaceholder.typicode.com/posts`
- **Method:** `POST`
- **Request Body:**
```json
{
    "title": "foo",
    "body": "bar",
    "userId": 1
}
```
- **Response Body:**
```json
{
    "id": 101,
    "title": "foo",
    "body": "bar",
    "userId": 1
}
```

### Example: Sending a POST request using WebClient
```java
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientPostExample {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");
        
        Mono<String> responseMono = webClient.post()
                .uri("/posts")
                .bodyValue("{"title":"foo","body":"bar","userId":1}")
                .retrieve()
                .bodyToMono(String.class);
        
        responseMono.subscribe(response -> System.out.println("Response: " + response));
    }
}
```

---

## `application.properties` vs `application.yml`
Spring Boot allows configuration using either `application.properties` or `application.yml`.

### **1. `application.properties` (Key-Value Format)**
- Uses simple key-value pairs
- Example:
```properties
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=secret
```

### **2. `application.yml` (YAML Format - More Readable)**
- Uses hierarchical structure
- Example:
```yaml
server:
  port: 8081
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: secret
```

### **Comparison:**
| Feature              | `application.properties` | `application.yml` |
|----------------------|-------------------------|-------------------|
| Readability         | Less readable            | More readable    |
| Hierarchical Config | No                        | Yes             |
| Multi-line Strings  | Harder to manage         | Easier to manage |
| Comments Support    | Yes (using `#`)          | Yes (using `#` or inline `#`) |
| Widely Used        | Yes                        | Yes (preferred for complex configs) |

---

## Conclusion
- `RestTemplate` is **deprecated** and supports only **synchronous** communication.
- `WebClient` is **recommended**, supporting both **synchronous and asynchronous** modes.
- **Choose `application.properties` for simple configurations and `application.yml` for complex ones.**

