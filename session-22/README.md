# Spring Boot Actuators

## Introduction
Spring Boot Actuator is a powerful feature introduced in Spring Boot that provides production-ready features to monitor and manage applications. It exposes various built-in endpoints that allow developers and administrators to gain insights into the running application.

## Adding Actuator to Your Project
To enable Actuator, add the following dependency in your `pom.xml` file:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

For Gradle users, add the following to `build.gradle`:

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
}
```

## Actuator Endpoints
Spring Boot Actuator provides several built-in endpoints that help in monitoring and managing the application. Some of the most commonly used endpoints include:

| Endpoint | Description |
|----------|-------------|
| `/actuator/health` | Provides application health status (enabled by default). |
| `/actuator/info` | Displays custom application info. |
| `/actuator/beans` | Shows all loaded beans in the application context. |
| `/actuator/mappings` | Lists all available URL mappings. |
| `/actuator/configprops` | Displays configuration properties. |
| `/actuator/env` | Shows environment properties. |
| `/actuator/metrics` | Exposes various metrics related to the application. |
| `/actuator/heapdump` | Downloads heap memory dump. |
| `/actuator/threaddump` | Shows thread dump for the application. |
| `/actuator/loggers` | Allows log level configuration dynamically. |
| `/actuator/shutdown` | Stops the application (must be explicitly enabled). |

## Configuring Actuator Endpoints
By default, only the `/health` and `/info` endpoints are enabled. To enable additional endpoints, configure the `application.yml` or `application.properties` file as shown below:

### Enabling All Endpoints
**application.yml:**
```yaml
management:
  endpoints:
    web:
      exposure:
        include: '*'
```

**application.properties:**
```properties
management.endpoints.web.exposure.include=*
```

### Enabling Specific Endpoints
If you want to enable only a few endpoints, specify them explicitly:

**application.yml:**
```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
```

**application.properties:**
```properties
management.endpoints.web.exposure.include=health,info,metrics
```

### Disabling Specific Endpoints
To disable a particular endpoint, set:

**application.yml:**
```yaml
management:
  endpoint:
    shutdown:
      enabled: false
```

**application.properties:**
```properties
management.endpoint.shutdown.enabled=false
```

### Customizing Health Endpoint
You can add custom health indicators or modify existing ones:

**application.yml:**
```yaml
management:
  endpoint:
    health:
      show-details: always
```

**application.properties:**
```properties
management.endpoint.health.show-details=always
```

## Secure Actuator Endpoints
For security reasons, it is recommended to secure Actuator endpoints in production environments.

**Example Security Configuration:**

```yaml
spring:
  security:
    user:
      name: admin
      password: admin123
management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    shutdown:
      enabled: true
  health:
    show-details: always
```

## Customizing Application Info
You can add custom application details in the `application.yml`:

```yaml
info:
  app:
    name: My Spring Boot App
    description: Spring Boot application with Actuator
    version: 1.0.0
```

These details will be available at `/actuator/info`.

## Enabling Shutdown Endpoint
By default, the `/shutdown` endpoint is disabled for safety. To enable it:

```yaml
management:
  endpoint:
    shutdown:
      enabled: true
```

**Trigger Shutdown:**
```bash
curl -X POST http://localhost:8080/actuator/shutdown
```

## Conclusion
Spring Boot Actuator is a crucial tool for monitoring and managing Spring Boot applications. It provides various endpoints to inspect application health, metrics, logs, and other configurations. By properly configuring Actuator, you can ensure better observability and control over your application.

