# Lombok Project: Simplifying Java Code

**Lombok** is a popular third-party library for Java that helps reduce boilerplate code in your projects. It automatically generates common methods like getters, setters, constructors, `toString()`, `equals()`, and `hashCode()` at compile time, making your code cleaner and more maintainable.

---

## Key Features of Lombok

Lombok provides annotations to generate the following for your classes:

1. **Getters and Setters**:
    - Automatically generates `getX()` and `setX()` methods for all fields.
    - Annotations: `@Getter`, `@Setter`.

2. **Constructors**:
    - Generates a no-argument constructor (`@NoArgsConstructor`).
    - Generates a constructor with parameters for all fields (`@AllArgsConstructor`).

3. **`toString()` Method**:
    - Generates a `toString()` method that includes all fields.
    - Annotation: `@ToString`.

4. **`equals()` and `hashCode()` Methods**:
    - Generates `equals()` and `hashCode()` methods based on all fields.
    - Annotation: `@EqualsAndHashCode`.

5. **`@Data` Annotation**:
    - A shortcut annotation that combines `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, `@RequiredArgsConstructor`, and `@NoArgsConstructor`.

---

## Project Overview

This project demonstrates how to use Lombok annotations to simplify Java classes. It includes two classes (`Person` and `User`) and a main application to test the functionality.

---

## Project Structure

### 1. **Person Class**
- Uses Lombok annotations to generate getters, setters, constructors, `toString()`, `equals()`, and `hashCode()`.
   ```java
   package com.ragheb.dto;

   import lombok.*;

   import java.util.Date;

   @Setter
   @Getter
   @ToString
   @EqualsAndHashCode
   @AllArgsConstructor
   @NoArgsConstructor
   //@Data
   public class Person {
       private Integer id;
       private String name;
       private String gender;
       private String phone;
       private Date birthday;
   }
   ```

### 2. **User Class**
- Similar to the `Person` class, but with different fields.
   ```java
   package com.ragheb.dto;

   import lombok.*;

   @Setter
   @Getter
   @ToString
   @EqualsAndHashCode
   @AllArgsConstructor
   @NoArgsConstructor
   //@Data
   public class User {
       private Integer id;
       private String username;
       private String password;
       private String email;
       private String phone;
       private String name;
   }
   ```

### 3. **Main Application**
- Demonstrates how to use the `Person` class with Lombok-generated methods.
   ```java
   package com.ragheb;

   import com.ragheb.dto.Person;
   import java.util.Date;

   public class App {
       public static void main(String[] args) {
           Person person = new Person();
           person.setId(1);
           person.setName("Ragheb");
           person.setGender("Male");
           person.setBirthday(new Date());
           person.setPhone("123456789");
           System.out.println(person);
       }
   }
   ```

---

## How Lombok Works

1. **Annotations**:
    - Lombok annotations (e.g., `@Getter`, `@Setter`, `@ToString`) are added to the class.
    - These annotations tell Lombok what methods to generate.

2. **Compile-Time Code Generation**:
    - During compilation, Lombok processes the annotations and generates the corresponding methods (e.g., getters, setters, `toString()`).

3. **No Runtime Overhead**:
    - Lombok-generated code is added at compile time, so there is no runtime dependency on Lombok.

---

## Benefits of Using Lombok

1. **Reduces Boilerplate Code**:
    - Eliminates the need to manually write repetitive code like getters, setters, and constructors.

2. **Improves Readability**:
    - Makes classes shorter and easier to read by removing clutter.

3. **Saves Time**:
    - Automates the generation of common methods, allowing developers to focus on business logic.

4. **Maintainable Code**:
    - Changes to fields (e.g., adding a new field) automatically update the generated methods.

---

## Example Output

When you run the `App` class, the output will be:
```
Person(id=1, name=Ragheb, gender=Male, phone=123456789, birthday=Wed Oct 25 12:34:56 UTC 2023)
```

---

## How to Set Up Lombok in Your Project

### 1. **Add Lombok Dependency**
- For Maven, add the following to your `pom.xml`:
  ```xml
  <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.30</version> <!-- Use the latest version -->
      <scope>provided</scope>
  </dependency>
  ```

### 2. **Enable Annotation Processing**
- In your IDE (e.g., IntelliJ IDEA or Eclipse), enable annotation processing to ensure Lombok works correctly.

### 3. **Install Lombok Plugin (Optional)**
- Install the Lombok plugin for your IDE to avoid errors and enable features like auto-completion.

---

## Key Annotations in Lombok

| Annotation          | Description                                                                 |
|---------------------|-----------------------------------------------------------------------------|
| `@Getter`           | Generates getter methods for all fields.                                    |
| `@Setter`           | Generates setter methods for all fields.                                    |
| `@ToString`         | Generates a `toString()` method.                                            |
| `@EqualsAndHashCode`| Generates `equals()` and `hashCode()` methods.                              |
| `@NoArgsConstructor`| Generates a no-argument constructor.                                        |
| `@AllArgsConstructor`| Generates a constructor with parameters for all fields.                    |
| `@Data`             | Combines `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, etc.      |

---

## Conclusion

Lombok is a powerful tool for Java developers that significantly reduces boilerplate code and improves productivity. By using Lombok annotations, you can write cleaner, more maintainable code without sacrificing functionality.

This project demonstrates how to use Lombok to simplify Java classes and automate the generation of common methods. Incorporate Lombok into your projects to save time and focus on what matters most—building great software!