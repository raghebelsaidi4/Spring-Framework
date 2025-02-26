The **Circuit Breaker** pattern is used to provide fault tolerance in distributed systems by preventing repeated failures when a service is down or responding slowly. In Spring Boot, we implement it using **Resilience4j**.

---

## **Steps to Implement Circuit Breaker in Spring Boot**
1. **Create a Spring Boot Application**
2. **Add Resilience4j Dependencies**
3. **Enable Circuit Breaker**
4. **Create a Service with Fault Simulation**
5. **Apply Circuit Breaker Annotation**
6. **Test the Circuit Breaker Functionality**

---

### **1. Add Dependencies (`pom.xml`)**
```xml
<dependencies>
    <!-- Spring Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Resilience4j Circuit Breaker -->
    <dependency>
        <groupId>io.github.resilience4j</groupId>
        <artifactId>resilience4j-spring-boot2</artifactId>
        <version>1.7.1</version>
    </dependency>

    <!-- Spring Boot Actuator (for monitoring) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
</dependencies>
```

---

### **2. Enable Circuit Breaker in the Main Application**
Create a **Spring Boot main class** and enable **Resilience4j Circuit Breaker**.

```java
@SpringBootApplication
@EnableCircuitBreaker
public class CircuitBreakerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CircuitBreakerApplication.class, args);
    }
}
```

---

### **3. Create a Service with Fault Simulation**
In this step, we'll simulate a failure scenario in our service.

```java
@Service
public class ExternalService {

    private static final Logger logger = LoggerFactory.getLogger(ExternalService.class);

    private int requestCount = 0;

    @CircuitBreaker(name = "externalService", fallbackMethod = "fallbackResponse")
    public String fetchData() {
        requestCount++;
        logger.info("Request count: {}", requestCount);

        if (requestCount % 5 != 0) { // Simulate failure 4 out of 5 times
            throw new RuntimeException("Service Down!");
        }
        return "Data from External Service";
    }

    public String fallbackResponse(Exception e) {
        return "Fallback Response: External Service is temporarily unavailable!";
    }
}
```

---

### **4. Create a REST Controller to Call the Service**
```java
@RestController
@RequestMapping("/api")
public class CircuitBreakerController {

    @Autowired
    private ExternalService externalService;

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchData() {
        String response = externalService.fetchData();
        return ResponseEntity.ok(response);
    }
}
```

---

### **5. Configure Circuit Breaker in `application.yml`**
```yaml
resilience4j:
  circuitbreaker:
    instances:
      externalService:
        sliding-window-size: 10
        minimum-number-of-calls: 5
        failure-rate-threshold: 50
        wait-duration-in-open-state: 5000ms
        permitted-number-of-calls-in-half-open-state: 3
        automatic-transition-from-open-to-half-open-enabled: true
```
- `sliding-window-size: 10` → Checks the last 10 calls.
- `minimum-number-of-calls: 5` → Needs at least 5 calls to activate the circuit breaker.
- `failure-rate-threshold: 50` → If 50% of the calls fail, the circuit breaker trips.
- `wait-duration-in-open-state: 5000ms` → The circuit stays open for 5 seconds before retrying.
- `automatic-transition-from-open-to-half-open-enabled: true` → Allows automatic recovery.

---

### **6. Testing the Circuit Breaker**
#### **1. Run the Application**
```sh
mvn spring-boot:run
```

#### **2. Call the API Multiple Times**
```sh
curl http://localhost:8080/api/fetch
```
- **1st-4th calls:** Fail because the simulated failure happens.
- **5th call:** Success, because of the condition in the service.

#### **3. Observe Circuit Breaker Behavior**
- If failures exceed 50%, the circuit breaker **opens** and blocks requests for 5 seconds.
- If the service recovers, the circuit **closes** and allows traffic again.

---

### **7. Circuit Breaker States**
1. **Closed**: The service is working fine.
2. **Open**: The service is down, and further requests return fallback.
3. **Half-Open**: The circuit tries a few test requests to see if recovery is possible.

---

### **8. Monitor Circuit Breaker Using Actuator**
Add the following to `application.yml`:
```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

Now, check the circuit breaker status:
```sh
curl http://localhost:8080/actuator/circuitbreakers
```

---

## **Summary**
✅ Implemented **Resilience4j Circuit Breaker**  
✅ Configured fault tolerance and fallback method  
✅ Monitored Circuit Breaker state with **Spring Boot Actuator**

This ensures that our service remains **resilient and fault-tolerant** even when dependencies fail. 🚀 Let me know if you need further improvements!