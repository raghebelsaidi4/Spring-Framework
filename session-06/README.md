# Spring Annotations 

Spring annotations simplify the configuration of Spring applications by reducing the need for XML configuration. They provide a declarative way to define beans, manage dependencies, and configure the application context. Below is an explanation of the key Spring annotations used in this project, along with examples.

---

## Key Spring Annotations

### 1. **@Configuration**
- Indicates that a class declares one or more `@Bean` methods and can be processed by the Spring container to generate bean definitions.
- Example:
  ```java
  @Configuration
  @ComponentScan(basePackages = "com.system")
  public class AppConfig {
  }
  ```

### 2. **@ComponentScan**
- Configures component scanning directives for use with `@Configuration` classes. It tells Spring where to look for components (e.g., `@Component`, `@Service`, `@Repository`, etc.).
- Example:
  ```java
  @ComponentScan(basePackages = "com.system")
  ```

### 3. **@Repository**
- Marks a class as a **Data Access Object (DAO)**. It is a specialization of `@Component` and is used for database operations.
- Example:
  ```java
  @Repository
  public class MysqlDBDAO implements ReportDAO {
      @Override
      public void getData() {
          System.out.println("getting data from MysqlDB");
      }
  }
  ```

### 4. **@Component**
- A generic stereotype annotation for any Spring-managed component. It is a general-purpose annotation for any bean.
- Example:
  ```java
  @Component
  public class MyComponent {
  }
  ```

### 5. **@Bean**
- Indicates that a method produces a bean to be managed by the Spring container. It is typically used in `@Configuration` classes.
- Example:
  ```java
  @Bean
  public ReportDAO oracleDBDAO() {
      return new OracleDBDAO();
  }
  ```

### 6. **@Autowired**
- Marks a constructor, field, or setter method to be autowired by Spring's dependency injection facilities.
- It can be used in three places:
    1. **Variable Level**:
       ```java
       @Autowired
       private ReportDAO reportDAO;
       ```
    2. **Constructor Level**:
       ```java
       @Autowired
       public ReportService(ReportDAO reportDAO) {
           this.reportDAO = reportDAO;
       }
       ```
    3. **Setter Method Level**:
       ```java
       @Autowired
       public void setReportDAO(ReportDAO reportDAO) {
           this.reportDAO = reportDAO;
       }
       ```

### 7. **@Service**
- Marks a class as a **service component** in the service layer. It is a specialization of `@Component`.
- Example:
  ```java
  @Service
  public class ReportService {
      @Autowired
      private ReportDAO reportDAO;

      public void generateReport() {
          reportDAO.getData();
          System.out.println("Report generated ...");
      }
  }
  ```

### 8. **@Scope**
- Configures the scope of a bean (e.g., `singleton`, `prototype`, `request`, `session`).
- Example:
  ```java
  @Scope("prototype")
  @Component
  public class MyPrototypeBean {
  }
  ```

### 9. **@Qualifier**
- Used to resolve ambiguity when multiple beans of the same type are available. It specifies which bean should be injected.
- Example:
  ```java
  @Qualifier("mysqlDBDAO")
  @Autowired
  private ReportDAO reportDAO;
  ```

### 10. **@Primary**
- Indicates that a bean should be given preference when multiple candidates are qualified to autowire a single-valued dependency.
- Example:
  ```java
  @Primary
  @Repository
  public class MysqlDBDAO implements ReportDAO {
      @Override
      public void getData() {
          System.out.println("getting data from MysqlDB");
      }
  }
  ```

---

## Project Structure and Explanation

### 1. **DAO Layer**
- **`ReportDAO` Interface**:
  ```java
  package com.system.dao;

  public interface ReportDAO {
      public void getData();
  }
  ```
- **`MysqlDBDAO` Class**:
  ```java
  package com.system.beans;

  import com.system.dao.ReportDAO;
  import org.springframework.stereotype.Repository;

  @Repository
  public class MysqlDBDAO implements ReportDAO {
      @Override
      public void getData() {
          System.out.println("getting data from MysqlDB");
      }
  }
  ```
- **`OracleDBDAO` Class**:
  ```java
  package com.system.beans;

  import com.system.dao.ReportDAO;
  import org.springframework.stereotype.Repository;

  @Repository
  public class OracleDBDAO implements ReportDAO {
      @Override
      public void getData() {
          System.out.println("getting data from Oracle Database");
      }
  }
  ```

### 2. **Service Layer**
- **`ReportService` Class**:
  ```java
  package com.system.service;

  import com.system.dao.ReportDAO;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.beans.factory.annotation.Qualifier;
  import org.springframework.stereotype.Service;

  @Service
  public class ReportService {
      @Qualifier("mysqlDBDAO")
      @Autowired
      private ReportDAO reportDAO;

      public void generateReport() {
          reportDAO.getData();
          System.out.println("Report generated ...");
      }
  }
  ```

### 3. **Configuration Layer**
- **`AppConfig` Class**:
  ```java
  package com.system.config;

  import org.springframework.context.annotation.ComponentScan;
  import org.springframework.context.annotation.Configuration;

  @Configuration
  @ComponentScan(basePackages = "com.system")
  public class AppConfig {
  }
  ```

### 4. **Main Application**
- **`App` Class**:
  ```java
  package com.system;

  import com.system.config.AppConfig;
  import com.system.service.ReportService;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.annotation.AnnotationConfigApplicationContext;

  public class App {
      public static void main(String[] args) {
          ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
          ReportService service = context.getBean(ReportService.class);
          service.generateReport();
      }
  }
  ```

---

## Output

When you run the application, the output will be:
```
getting data from MysqlDB
Report generated ...
```

---

## Key Takeaways

1. **Annotations Simplify Configuration**:
    - Annotations like `@Component`, `@Service`, and `@Repository` eliminate the need for XML configuration.
    - `@Autowired` and `@Qualifier` make dependency injection straightforward.

2. **Flexibility with `@Qualifier` and `@Primary`**:
    - Use `@Qualifier` to specify which bean to inject when multiple beans of the same type exist.
    - Use `@Primary` to give preference to a specific bean.

3. **Component Scanning**:
    - `@ComponentScan` ensures that Spring automatically detects and registers beans in the specified package.

4. **Configuration with `@Configuration`**:
    - `@Configuration` classes provide a programmatic way to configure the Spring container.
