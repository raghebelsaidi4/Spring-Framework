# Apache Kafka with Spring Boot

## Overview
Apache Kafka is an open-source distributed event streaming platform used for building real-time data pipelines and streaming applications. In our microservices architecture, we will use Apache Kafka as a messaging broker, enabling event-driven communication between services.

### Key Concepts:
- **Publisher**: The application that publishes messages to Kafka.
- **Subscriber**: The application that subscribes to and consumes messages from Kafka.
- **Message Broker**: Kafka acts as an intermediary to facilitate communication between publishers and subscribers.

Using Apache Kafka, we can develop **Event-Driven Microservices**, enabling real-time communication between different services.

---

## Config Server
In our application development, we use various configuration properties. Initially, these configurations are stored in `application.properties` or `application.yml` files. However, modifying configurations requires repackaging and redeploying the application, which is inefficient.

To overcome this limitation, we use **Spring Cloud Config Server** to externalize configuration properties, making them dynamically available to applications without requiring redeployment.

### Use Cases:
- Database connection properties
- Server port numbers
- External API URLs
- Feature toggles

---

## Setting Up Config Server
### Steps to Create Config Server:
1. Create a **Spring Boot** project and add dependencies:
   ```xml
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-config-server</artifactId>
   </dependency>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   ```
2. Annotate the main application class with `@EnableConfigServer`:
   ```java
   @SpringBootApplication
   @EnableConfigServer
   public class ConfigServerApplication {
       public static void main(String[] args) {
           SpringApplication.run(ConfigServerApplication.class, args);
       }
   }
   ```
3. Configure `application.properties`:
   ```properties
   spring.application.name=config-server
   server.port=8888
   spring.cloud.config.server.git.uri=https://github.com/suresh-cloud-config/config-repo
   spring.cloud.config.server.git.clone-on-start=true
   ```
4. Run the application and access the configuration by visiting:
   ```
   http://localhost:8888/{application-name}/{profile}
   ```

---

## Config Client - Loading Configurations from Config Server
### Steps to Create Config Client Application:
1. Create a **Spring Boot** application and add dependencies:
   ```xml
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-config</artifactId>
   </dependency>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
   </dependency>
   ```
2. Configure `bootstrap.yml` to connect with Config Server:
   ```yaml
   spring:
     application:
       name: my-microservice
     cloud:
       config:
         uri: http://localhost:8888
   ```
3. Create a REST Controller to access properties:
   ```java
   @RestController
   @RequestMapping("/config")
   public class ConfigController {
       
       @Value("${app.message: Default message}")
       private String message;
       
       @GetMapping("/message")
       public String getMessage() {
           return message;
       }
   }
   ```
4. Run the application and test:
   ```
   http://localhost:8080/config/message
   ```

---

## Examples of Apache Kafka with Spring Boot
### 1. Setting Up Apache Kafka in Spring Boot
#### Step 1: Add Dependencies
```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

#### Step 2: Configure Kafka Properties (`application.yml`)
```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: my-group
    producer:
      retries: 3
```

#### Step 3: Create a Producer Service
```java
@Service
public class KafkaProducerService {
    
    private static final String TOPIC = "my-topic";
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
```

#### Step 4: Create a Consumer Service
```java
@Service
public class KafkaConsumerService {
    
    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Received Message: " + message);
    }
}
```

#### Step 5: Expose REST Endpoint to Publish Messages
```java
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    
    @Autowired
    private KafkaProducerService kafkaProducerService;
    
    @GetMapping("/publish")
    public String publishMessage(@RequestParam("message") String message) {
        kafkaProducerService.sendMessage(message);
        return "Message Published!";
    }
}
```

#### Step 6: Test the Kafka Setup
1. Start Zookeeper & Kafka server.
2. Start the Spring Boot application.
3. Publish a message:
   ```
   http://localhost:8080/kafka/publish?message=HelloKafka
   ```
4. Check the logs for the received message.

---

## Conclusion
- Apache Kafka is a powerful tool for event-driven microservices.
- Spring Cloud Config Server helps in managing application configurations dynamically.
- With Kafka, microservices can communicate asynchronously in a scalable manner.

