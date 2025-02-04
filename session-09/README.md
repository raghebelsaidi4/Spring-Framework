# Spring Boot
Spring Boot is a framework that simplifies the development of Spring-based applications by reducing configuration overhead. It allows developers to build various types of applications with minimal setup.

## Types of Applications
Using Spring Boot, we can develop:
1. **Standalone Applications**
2. **Web Applications**
3. **Distributed Applications (Web Services)**

## Advantages of Spring Boot

### 1. Starter POMs
Spring Boot provides pre-configured starter dependencies to simplify Maven/Gradle build configurations:
- `spring-boot-starter-web` (for web applications)
- `spring-boot-starter-data-jpa` (for JPA and database connectivity)
- `spring-boot-starter-security` (for security configurations)
- `spring-boot-starter-mail` (for sending emails)

### 2. Auto Configuration
Spring Boot reduces manual configuration by automatically:
- Creating a database connection pool
- Deploying a web application in an embedded server
- Starting the IoC container
- Enabling component scanning

### 3. Actuators
Spring Boot Actuator provides tools for monitoring and managing applications. It allows us to check:
- Loaded beans count
- URL mappings
- Configuration properties
- Application health status
- Heap dump and thread dump information

### 4. Embedded Servers
Spring Boot provides built-in servers, making it easy to deploy applications without requiring an external application server:
- **Apache Tomcat** (default)
- **Jetty**
- **Netty**

## Spring Boot = Spring Framework - XML Config + Auto Configuration + Embedded Servers + Actuators

## What is the Start Class in Spring Boot?
The **start class** serves as the entry point for executing a Spring Boot application. It is also referred to as the **main class**.

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```
### Breakdown of `@SpringBootApplication`
It is a combination of three annotations:
- `@Configuration` – Marks this class as a configuration class
- `@EnableAutoConfiguration` – Enables auto-configuration
- `@ComponentScan` – Enables component scanning

## How Does IoC Start in Spring Boot?
Spring Boot initializes the IoC (Inversion of Control) container using `SpringApplication.run()`.
- `spring-boot-starter` → Uses `AnnotationConfigApplicationContext`
- `spring-boot-starter-web` → Uses `AnnotationConfigServletWebServerApplicationContext`
- `spring-boot-starter-webflux` → Uses `AnnotationConfigReactiveWebApplicationContext`

## What is a Banner in Spring Boot?
The **banner** is the Spring Boot logo that prints in the console when the application starts.
Spring Boot provides three banner modes:
1. **Console (default)** – Prints the banner in the console.
2. **Log** – Prints the banner in the log file.
3. **Off** – Disables the banner.

### Customizing the Banner
We can modify the banner by adding a `banner.txt` file under `src/main/resources/`.

To disable the banner, set the following property in `application.properties`:
```properties
spring.main.banner-mode=off
```

## What is a Runner in Spring Boot?
Spring Boot provides runners to execute logic **only once** when the application starts. There are two types:
1. **`ApplicationRunner`** – Implements the `run(ApplicationArguments args)` method.
2. **`CommandLineRunner`** – Implements the `run(String... args)` method.

Both are functional interfaces with a single abstract method.

### Use Cases
- Loading static data into tables at startup
- Deleting old/stale data from staging tables
- Sending startup notifications

### Example of `CommandLineRunner`
```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started! Running initialization logic...");
    }
}
```

### Example of `ApplicationRunner`
```java
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Application started with arguments: " + args.getOptionNames());
    }
}
```

## `@DependsOn` Annotation
The `@DependsOn` annotation ensures that a specific bean is initialized **before** another bean that depends on it.

### Example 1: Setting Dependency Between Beans
```java
import org.springframework.stereotype.Component;

@Component("beanA")
public class BeanA {
    public BeanA() {
        System.out.println("BeanA Initialized");
    }
}

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.DependsOn;

@Component("beanB")
@DependsOn("beanA")
public class BeanB {
    public BeanB() {
        System.out.println("BeanB Initialized after BeanA");
    }
}
```
### Example 2: Ensuring Order in Service Initialization
```java
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.DependsOn;

@Service("databaseService")
public class DatabaseService {
    public DatabaseService() {
        System.out.println("Database Service Initialized");
    }
}

@Service("cacheService")
@DependsOn("databaseService")
public class CacheService {
    public CacheService() {
        System.out.println("Cache Service Initialized after DatabaseService");
    }
}
```
### Example 3: Using `@DependsOn` in Configuration Classes
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class AppConfig {
    @Bean(name = "beanX")
    public BeanX beanX() {
        return new BeanX();
    }

    @Bean(name = "beanY")
    @DependsOn("beanX")
    public BeanY beanY() {
        return new BeanY();
    }
}
```
### When to Use `@DependsOn`
- Ensuring correct initialization order for dependent beans.
- Managing dependencies between services (e.g., cache service requires a database service).
- Controlling bean creation sequence in complex configurations.

---

