# Microservices Mini Project Implementation Guide

## 1. Service Registry Setup (Eureka Server)
### Dependencies Required
- spring-cloud-netflix-eureka-server

### Configuration Steps
1. Add @EnableEurekaServer annotation to main class
2. Configure in application.yml:
    - Set server.port=8761
    - Set eureka.client.register-with-eureka=false
3. Access dashboard at http://localhost:8761/

## 2. Admin Server Configuration
### Dependencies Required
- web-starter
- devtools
- admin-server (code centric)

### Configuration Steps
1. Add @EnableAdminServer annotation to main class
2. Configure custom embedded container port
3. Access dashboard at http://localhost:{port}/

## 3. Zipkin Server Integration
- Download and run Zipkin server instance

## 4. REST API Development (WELCOME API)
### Dependencies Required
- eureka-discovery-client
- admin-client
- zipkin
- sleuth (for logging)
- web-starter
- devtools
- actuator
- feign client

### Implementation Steps
1. Add @EnableDiscoveryClient annotation to main class
2. Create REST controller with required endpoints
3. Implement Feign client for WELCOME API access
4. Configure in application.yml:
    - Server port
    - Admin server URL
    - Actuator endpoints
5. Verify integration through:
    - Eureka dashboard
    - Admin server dashboard
    - Zipkin dashboard

## 5. API Gateway Implementation
### Dependencies Required
- eureka-client
- cloud-gateway
- web-starter
- devtools

### Configuration Steps
1. Add @EnableDiscoveryClient annotation to main class
2. Configure API routing in application.yml
3. Verify registration in Eureka dashboard

## Additional Features
- Filter implementation for client request information access
- Request validation through captured client information

# Microservices Mini Project Implementation

This guide walks you through creating a microservices project with Eureka Service Registry, Spring Boot Admin, Zipkin Distributed Tracing, a REST API service, and an API Gateway.

---

## Step 1: Eureka Server (Service Registry)

**1. Create a Spring Boot Project**  
Add the following dependency in `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

**2. Main Class**  
Add `@EnableEurekaServer`:
```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

**3. Configure `application.yml`**
```yml
server:
  port: 8761

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

**4. Run the Application**  
Access the Eureka dashboard at: http://localhost:8761/

---

## Step 2: Spring Boot Admin Server

**1. Create a Spring Boot Project**  
Add dependencies to `pom.xml`:
```xml
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-server</artifactId>
    <version>2.7.0</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

**2. Main Class**  
Add `@EnableAdminServer`:
```java
@SpringBootApplication
@EnableAdminServer
public class AdminServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }
}
```

**3. Configure `application.yml`**
```yml
server:
  port: 9090
```

**4. Run the Application**  
Access the Admin dashboard at: http://localhost:9090/

---

## Step 3: Zipkin Server

**1. Download Zipkin Server**  
Download the [latest JAR](https://zipkin.io/pages/quickstart.html) or use Docker:
```bash
docker run -d -p 9411:9411 openzipkin/zipkin
```

**2. Run Zipkin**  
Start the JAR:
```bash
java -jar zipkin-server-*.jar
```

Access Zipkin at: http://localhost:9411/

---

## Step 4: Welcome API Service

**1. Create a Spring Boot Project**  
Add dependencies to `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>2.7.0</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-sleuth-zipkin</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

**2. Main Class**  
Add annotations:
```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class WelcomeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WelcomeServiceApplication.class, args);
    }
}
```

**3. Create a REST Controller**
```java
@RestController
@RequestMapping("/welcome")
public class WelcomeController {
    @GetMapping
    public String welcome() {
        return "Welcome to Microservices!";
    }
}
```

**4. Configure `application.yml`**
```yml
server:
  port: 8081

spring:
  application:
    name: WELCOME-SERVICE
  boot:
    admin:
      client:
        url: http://localhost:9090
  zipkin:
    base-url: http://localhost:9411
  sleuth:
    sampler:
      probability: 1.0

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka

management:
  endpoints:
    web:
      exposure:
        include: "*"
```

**5. Run the Application**  
Verify registration in Eureka and Spring Boot Admin.

---

## Step 5: API Gateway

**1. Create a Spring Boot Project**  
Add dependencies to `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

**2. Main Class**  
Add `@EnableDiscoveryClient`:
```java
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
```

**3. Configure `application.yml`**
```yml
server:
  port: 8080

spring:
  application:
    name: API-GATEWAY
  cloud:
    gateway:
      routes:
        - id: welcome-service
          uri: lb://WELCOME-SERVICE
          predicates:
            - Path=/api/welcome/**

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```

**4. Add a Custom Filter (Example)**  
Create a filter to log requests:
```java
@Component
public class LoggingFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Request path: " + exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}
```

**5. Run the Application**  
Access the Welcome API via Gateway: http://localhost:8080/api/welcome

---

## Verify All Components

1. **Eureka Dashboard**: http://localhost:8761 – Confirm all services are registered.
2. **Spring Boot Admin**: http://localhost:9090 – Monitor service health and metrics.
3. **Zipkin**: http://localhost:9411 – View distributed traces.
4. **API Gateway**: http://localhost:8080/api/welcome – Test the routed request.