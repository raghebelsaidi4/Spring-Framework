# Spring Boot Form Validation with Thymeleaf

## Table of Contents
1. [Form Validation](#form-validation)
2. [Thymeleaf Integration](#thymeleaf-integration)
3. [Why Use Thymeleaf Over JSP](#why-use-thymeleaf-over-jsp)
4. [Switch to Jetty Server](#switch-to-jetty-server)
5. [Deploy as WAR File](#deploy-as-war-file)
6. [Examples](#examples)
    - [Form Validation Example](#form-validation-example)
    - [Thymeleaf Form Example](#thymeleaf-form-example)
7. [Configuration Notes](#configuration-notes)
8. [Key Differences: JSP vs Thymeleaf](#key-differences-jsp-vs-thymeleaf)

---

## Form Validation
Ensure data integrity using Jakarta Bean Validation annotations.

### Step 1: Add Validation Dependency
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### Step 2: Common Validation Annotations
| Annotation       | Purpose                                  |
|-----------------|----------------------------------------|
| `@NotBlank`     | Rejects empty strings                 |
| `@Size(min, max)` | Validates string length              |
| `@Email`        | Validates email format                |
| `@Pattern(regex)` | Matches regex pattern               |
| `@Min`/`@Max`   | Validates number ranges              |

---

## Thymeleaf Integration
A modern HTML5 templating engine with better performance than JSP.

### Key Features
- Natural templating (HTML files work in browsers without backend)
- Full Spring integration
- Rich expression language

### Add Thymeleaf Dependency
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

---

## Why Use Thymeleaf Over JSP
### Problems with JSP:
- **Server Load**: JSPs must be compiled into servlets, increasing server load.
- **Execution Complexity**: Requires a servlet container to execute.
- **Poor Readability**: Uses Java-like scriptlets mixed with HTML, making templates harder to maintain.
- **Limited Browser Preview**: Cannot be viewed directly in a browser without a server.

### Advantages of Thymeleaf:
- **Lightweight & Faster**: No servlet conversion needed, reducing server load.
- **Works Like HTML**: Can be previewed in browsers without execution.
- **More Readable**: Uses intuitive expressions (`th:*` attributes) for dynamic content.
- **Better Integration**: Works seamlessly with Spring Boot and Spring MVC.

---

## Switch to Jetty Server
### Step 1: Exclude Tomcat
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

### Step 2: Add Jetty
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```

---

## Deploy as WAR File
### Step 1: Modify Packaging
```xml
<packaging>war</packaging>
```

### Step 2: Update Main Class
```java
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### Step 3: Build WAR
```bash
mvn clean package
```

---

## Examples

### Form Validation Example
**Entity Class**
```java
public class User {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank
    private String email;

    @Size(min = 8, max = 20, message = "Password must be 8-20 characters")
    private String password;
}
```

**Controller**
```java
@PostMapping("/register")
public String submitForm(@Valid @ModelAttribute("user") User user, 
                        BindingResult result) {
    if (result.hasErrors()) {
        return "registration-form";
    }
    return "registration-success";
}
```

### Thymeleaf Form Example
**registration-form.html**
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
    <form th:action="@{/register}" th:object="${user}" method="post">
        <div>
            <label>Name:</label>
            <input type="text" th:field="*{name}">
            <span th:if="${#fields.hasErrors('name')}" 
                  th:errors="*{name}" class="error"></span>
        </div>
        
        <div>
            <label>Email:</label>
            <input type="email" th:field="*{email}">
            <span th:if="${#fields.hasErrors('email')}" 
                  th:errors="*{email}"></span>
        </div>
        
        <button type="submit">Register</button>
    </form>
</body>
</html>
```

**Controller for Thymeleaf**
```java
@GetMapping("/register")
public String showForm(Model model) {
    model.addAttribute("user", new User());
    return "registration-form";
}
```

---

## Configuration Notes
1. **Thymeleaf Template Location**:
    - Place HTML files in `src/main/resources/templates/`
2. **Static Resources**:
    - Store CSS/JS in `src/main/resources/static/`
3. **Auto-reload with DevTools**:
    - Enable automatic restart for faster development

---

## Key Differences: JSP vs Thymeleaf
| Feature              | JSP                          | Thymeleaf                   |
|----------------------|----------------------------|-----------------------------|
| Execution            | Server-side compilation    | Pure HTML templates         |
| Performance          | Slower (JSP-to-Servlet)    | Faster                      |
| Natural Templates    | No                         | Yes                         |
| Learning Curve       | Steeper (JSTL tags)        | Easier (HTML5-compatible)   |

---

