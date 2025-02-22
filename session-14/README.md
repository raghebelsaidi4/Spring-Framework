# Spring Web MVC

Spring Web MVC is a module within the Spring Framework designed to simplify the development of web applications. It provides a robust and flexible framework for building web applications and RESTful services. Below are some of the key features and components of Spring Web MVC.

## Key Features

1. **Form Binding**: Simplifies the process of binding form data to Java objects.
2. **Flexibility in Form Binding**: Offers flexibility in how form data is bound to objects.
3. **Multiple Presentation Technologies**: Supports various presentation technologies such as JSP and Thymeleaf.
4. **Form Tag Library**: Provides a rich set of tags to simplify form handling in JSPs.

## Getting Started with Spring Boot

To develop a web application using Spring Boot, you need to include the following starter dependency in your `pom.xml` file:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

This starter dependency provides support for:
1. **MVC-based web applications**
2. **RESTful services**
3. **Embedded container (Tomcat)**

## Spring Web MVC Architecture

The architecture of Spring Web MVC revolves around several key components:

1. **DispatcherServlet**: Acts as the front controller, handling all incoming requests and responses.
2. **HandlerMapper**: Identifies the appropriate controller to handle the request.
3. **Controller**: A Java class responsible for processing the request and returning a `ModelAndView` object.
4. **ModelAndView**: Contains the model data (key-value pairs) and the view name (logical file name).
5. **ViewResolver**: Resolves the view name to the actual view file and technology.
6. **View**: Renders the model data in the view file.

![Spring Web MVC Architecture](https://github.com/user-attachments/assets/8e6572c0-ab86-47f6-81a7-d640b907104a)

## Key Components Explained

### Controller
A controller is a Java class that handles incoming requests and returns the appropriate response. It is annotated with `@Controller` and contains methods mapped to specific URL patterns.

### DispatcherServlet
The `DispatcherServlet` is the central dispatcher for HTTP request handlers/controllers. It performs pre-processing and post-processing of requests and responses.

### HandlerMapper
The `HandlerMapper` identifies the appropriate controller to handle the incoming request based on the URL pattern.

### ModelAndView
The `ModelAndView` object is returned by the controller. It contains:
- **Model**: Represents data in key-value format.
- **View**: The logical name of the view file.

### ViewResolver
The `ViewResolver` identifies the location and technology of the presentation file based on the view name.

### View
The view is responsible for rendering the model data in the view file (e.g., JSP, Thymeleaf).

## Building Your First Web Application with Spring Boot

Follow these steps to create a simple web application using Spring Boot:

1. **Create a Spring Starter Project** with the following dependencies:
    - `spring-boot-starter-web`
    - `spring-boot-devtools`
    - `tomcat-embed-jasper`

2. **Create a Controller Class** with the required methods. For example:

    ```java
    @Controller
    public class HelloController {
        
        @GetMapping("/hello")
        public String sayHello(Model model) {
            model.addAttribute("message", "Hello, World!");
            return "hello";
        }
    }
    ```

3. **Map Controller Methods to URL Patterns** using annotations like `@GetMapping`, `@PostMapping`, etc.

4. **Create a View File** with the presentation logic. For example, create a `hello.jsp` file in the `src/main/webapp/WEB-INF/views` directory:

    ```jsp
    <html>
    <head>
        <title>Hello</title>
    </head>
    <body>
        <h1>${message}</h1>
    </body>
    </html>
    ```

5. **Configure the View Resolver** in the `application.properties` file:

    ```properties
    spring.mvc.view.prefix=/WEB-INF/views/
    spring.mvc.view.suffix=.jsp
    ```

6. **Run the Application** and test it by navigating to `http://localhost:8080/hello`.

## Adding Context Path

By default, Spring Boot web applications do not have a context path. You can add a context path by configuring the `application.properties` file:

```properties
server.servlet.context-path=/api
```

## Using Spring Boot DevTools

Spring Boot DevTools is a module that provides additional development-time features, such as automatic restarts and live reloads. To use it, include the following dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```

With DevTools, your server will automatically restart whenever you make changes to your code, improving the development experience.

## Conclusion

Spring Web MVC provides a powerful and flexible framework for building web applications. By following the steps outlined above, you can quickly set up and run a simple web application using Spring Boot. The architecture is designed to be modular and extensible, making it suitable for a wide range of web development needs.