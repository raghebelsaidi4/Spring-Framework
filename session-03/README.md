# Spring Framework Modules Overview

This document provides an overview of key Spring Framework modules and their roles in enterprise application development.

## 1. Spring Core
**Base Module**: Foundation of the Spring Framework  
**Key Concepts**:
- **IOC (Inversion of Control)**: Manages object lifecycle (Spring beans) and object creation
- **DI (Dependency Injection)**: Implements dependency management for loose coupling

Enables development of loosely coupled classes through:
- Bean configuration (XML/annotations)
- Application context management

## 2. Spring Context
**Configuration Management**:  
Provides advanced configuration capabilities through:
- `ApplicationContext` interface (enhanced `BeanFactory`)
- Support for internationalization
- Event propagation
- Resource loading

Key Annotations:
- `@Configuration`
- `@ComponentScan`
- `@PropertySource`

## 3. Spring AOP (Aspect-Oriented Programming)
**Cross-Cutting Concerns**:  
Separates secondary/helper logic from business logic using:
- **Aspects**: Modularization of cross-cutting concerns
- **Advice**: Action taken at specific join points
- **Pointcuts**: Predicates defining where advice applies

Common Use Cases:
- Security
- Transaction management
- Logging & auditing
- Performance monitoring

Example Aspect:
```java
@Aspect
public class LoggingAspect {
    @Before("execution(* com.example.service.*.*(..))")
    public void logMethodCall(JoinPoint jp) {
        System.out.println("Method called: " + jp.getSignature());
    }
}
```

## 4. Spring JDBC/DAO
**Simplified Database Operations**:  
Reduces JDBC boilerplate code through:

| Traditional JDBC              | Spring JDBC                 |
|-------------------------------|-----------------------------|
| 1. Load driver                | 1. Configure DataSource     |
| 2. Get connection             | 2. Use JdbcTemplate         |
| 3. Create statement           | 3. Execute SQL              |
| 4. Execute query              | 4. Process results          |
| 5. Process results            |                             |
| 6. Close connection           |                             |

Key Classes:
- `JdbcTemplate`
- `NamedParameterJdbcTemplate`
- `SimpleJdbcInsert`

## 5. Spring ORM
**Object-Relational Mapping**:  
Integrates with ORM frameworks:
- Hibernate
- JPA
- MyBatis
- JDO

Simplifies ORM operations:
```java
// Traditional Hibernate
SessionFactory factory = new Configuration().buildSessionFactory();
Session session = factory.openSession();
Transaction tx = session.beginTransaction();
// Perform operations
tx.commit();
session.close();

// Spring ORM
@Repository
public class ProductDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    public void saveProduct(Product product) {
        hibernateTemplate.save(product);
    }
}
```

## 6. Spring Web MVC
**Web Application Framework**:  
Implements Model-View-Controller pattern for web development:
- DispatcherServlet (Front Controller)
- Annotation-based controllers
- View resolution
- Form handling

Key Annotations:
- `@Controller`
- `@RequestMapping`
- `@GetMapping`/`@PostMapping`

## 7. Spring REST
**REST API Development**:  
Simplifies RESTful web services with:
- `@RestController` annotation
- HTTP response status handling
- Content negotiation
- JSON/XML serialization

Example REST Controller:
```java
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        // Implementation
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        // Implementation
    }
}
```

## Module Comparison Table

| Module         | Key Features                              | Typical Use Cases               |
|----------------|-------------------------------------------|---------------------------------|
| Spring Core    | IOC, DI, Bean management                  | Application foundation          |
| Spring Context | Advanced configuration, internationalization | Enterprise configuration       |
| Spring AOP     | Aspect programming                        | Logging, security, transactions |
| Spring JDBC    | Database access simplification            | SQL-based applications          |
| Spring ORM     | ORM integration                           | Hibernate/JPA projects          |
| Web MVC        | Web application framework                 | Traditional web apps            |
| Spring REST    | REST API development                      | Microservices, APIs             |

This modular architecture enables developers to select needed components while maintaining lightweight application structure.
