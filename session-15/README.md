# Spring Web MVC Form Handling Guide

This guide demonstrates how to create form-based applications using Spring Web MVC, covering data binding, form submission, and different ways to send data from controllers to the UI.

## Table of Contents
1. [Sending Data from Controller to UI](#sending-data-from-controller-to-ui)
2. [Form Development Steps](#steps-to-build-form-based-application)
3. [Examples](#examples)
    - [Example 1: User Registration Form (Using `Model`)](#example-1-user-registration-form-using-model)
    - [Example 2: Login Form (Using `ModelAndView`)](#example-2-login-form-using-modelandview)
    - [Example 3: Contact Form (Using `@ResponseBody`)](#example-3-contact-form-using-responsebody)
    - [Example 4: Search Form (Using `@RequestParam`)](#example-4-search-form-using-requestparam)
4. [Project Setup](#project-setup)

---

## Sending Data from Controller to UI
1. **`ModelAndView`**: Combines model data and a view name into a single object.
2. **`Model`**: Adds attributes to the model for rendering in the view.
3. **`@ResponseBody`**: Returns data directly (e.g., JSON/XML) instead of a view.

---

## Steps to Build Form-Based Application
1. **Create Spring Boot Project** with dependencies:
    - Spring Web Starter
    - DevTools
    - Lombok
    - Tomcat-Embed-Jasper (for JSP support)
2. **Create Form Binding Class**: A POJO to bind form data.
3. **Create Controller** with methods:
    - `GET` method to display the form.
    - `POST` method to handle form submission.
4. **Create View Pages**: Use JSP with Spring Form Tag Library.

---

## Examples

### Example 1: User Registration Form (Using `Model`)
**Step 1: Form Binding Class**
```java
// User.java
import lombok.Data;

@Data
public class User {
    private String name;
    private String email;
    private String password;
    private String gender;
}
```

**Step 2: Controller**
```java
// RegistrationController.java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "register-form";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("message", "Registration successful!");
        return "success";
    }
}
```

**Step 3: View (`register-form.jsp`)**
```jsp
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
    <h1>Registration Form</h1>
    <form:form action="/register" method="post" modelAttribute="user">
        Name: <form:input path="name"/><br/>
        Email: <form:input path="email"/><br/>
        Password: <form:password path="password"/><br/>
        Gender: 
        <form:radiobutton path="gender" value="Male"/> Male
        <form:radiobutton path="gender" value="Female"/> Female<br/>
        <input type="submit" value="Register"/>
    </form:form>
</body>
</html>
```

**Step 4: Success Page (`success.jsp`)**
```jsp
<h1>${message}</h1>
<p>Name: ${user.name}</p>
<p>Email: ${user.email}</p>
```

---

### Example 2: Login Form (Using `ModelAndView`)
**Controller**
```java
// LoginController.java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        ModelAndView mav = new ModelAndView("login-form");
        mav.addObject("loginForm", new LoginForm());
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView processLogin(LoginForm loginForm) {
        ModelAndView mav = new ModelAndView("login-success");
        mav.addObject("message", "Welcome, " + loginForm.getUsername() + "!");
        return mav;
    }
}
```

---

### Example 3: Contact Form (Using `@ResponseBody`)
**Controller**
```java
// ContactController.java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContactController {

    @PostMapping("/contact")
    @ResponseBody
    public String handleContactForm(Contact contact) {
        return "{\"status\": \"success\", \"message\": \"Message received!\"}";
    }
}
```

**AJAX Submission (JavaScript)**
```javascript
fetch("/contact", {
    method: "POST",
    body: JSON.stringify({subject: "Hello", message: "Test"}),
    headers: {"Content-Type": "application/json"}
})
.then(response => response.json())
.then(data => console.log(data));
```

---

### Example 4: Search Form (Using `@RequestParam`)
**Controller**
```java
// SearchController.java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String search(
            @RequestParam("query") String query,
            Model model) {
        model.addAttribute("results", performSearch(query));
        return "search-results";
    }
}
```

---

## Project Setup
1. **Dependencies** (`pom.xml`):
   ```xml
   <dependencies>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
       </dependency>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-devtools</artifactId>
       </dependency>
       <dependency>
           <groupId>org.projectlombok</groupId>
           <artifactId>lombok</artifactId>
           <optional>true</optional>
       </dependency>
       <dependency>
           <groupId>org.apache.tomcat.embed</groupId>
           <artifactId>tomcat-embed-jasper</artifactId>
       </dependency>
   </dependencies>
   ```

2. **JSP Location**: Place JSP files in `src/main/webapp/WEB-INF/views/`.

3. **Run Application**: Use `@SpringBootApplication` class to start the app. Access forms at:
    - `http://localhost:8080/register`
    - `http://localhost:8080/login`
    - `http://localhost:8080/contact`