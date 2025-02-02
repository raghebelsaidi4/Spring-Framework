# Spring Bean Life Cycle 

The Spring Bean Life Cycle refers to the sequence of steps that a Spring-managed bean goes through from its instantiation to its destruction. Spring provides hooks to execute custom logic at specific points in the bean's life cycle, such as after the bean is initialized or before it is destroyed.

---

## Key Life Cycle Methods

Spring allows you to define custom logic to execute during the following phases of a bean's life cycle:

1. **Initialization**: After the bean is instantiated and its dependencies are injected.
2. **Destruction**: Before the bean is removed from the Spring container.

You can implement these life cycle methods in three ways:

1. **Declarative Approach (XML Configuration)**
2. **Programmatic Approach (Implementing Interfaces)**
3. **Annotation Approach (Using Annotations)**

---

## 1. Declarative Approach (XML Configuration)

In this approach, you define the initialization and destruction methods in the Spring configuration file (`beans.xml`).

### Example

#### Bean Class
```java
package com.system.beans;

public class MyBean {
    public void init() {
        System.out.println("Bean is initialized using XML configuration.");
    }

    public void destroy() {
        System.out.println("Bean is destroyed using XML configuration.");
    }
}
```

#### XML Configuration (`beans.xml`)
```xml
<bean id="myBean" class="com.system.beans.MyBean" init-method="init" destroy-method="destroy"/>
```

#### Main Application
```java
package com.system;

import com.system.beans.MyBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        MyBean bean = context.getBean(MyBean.class);
        context.close(); // Triggers the destroy method
    }
}
```

#### Output
```
Bean is initialized using XML configuration.
Bean is destroyed using XML configuration.
```

---

## 2. Programmatic Approach (Implementing Interfaces)

In this approach, you implement the `InitializingBean` and `DisposableBean` interfaces to define the initialization and destruction logic.

### Example

#### Bean Class
```java
package com.system.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class MyBean implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean is initialized using InitializingBean interface.");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Bean is destroyed using DisposableBean interface.");
    }
}
```

#### XML Configuration (`beans.xml`)
```xml
<bean id="myBean" class="com.system.beans.MyBean"/>
```

#### Main Application
```java
package com.system;

import com.system.beans.MyBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        MyBean bean = context.getBean(MyBean.class);
        context.close(); // Triggers the destroy method
    }
}
```

#### Output
```
Bean is initialized using InitializingBean interface.
Bean is destroyed using DisposableBean interface.
```

---

## 3. Annotation Approach (Using Annotations)

In this approach, you use the `@PostConstruct` and `@PreDestroy` annotations to define the initialization and destruction logic.

### Example

#### Bean Class
```java
package com.system.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MyBean {
    @PostConstruct
    public void init() {
        System.out.println("Bean is initialized using @PostConstruct annotation.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Bean is destroyed using @PreDestroy annotation.");
    }
}
```

#### XML Configuration (`beans.xml`)
```xml
<context:annotation-config/>
<bean id="myBean" class="com.system.beans.MyBean"/>
```

#### Main Application
```java
package com.system;

import com.system.beans.MyBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        MyBean bean = context.getBean(MyBean.class);
        context.close(); // Triggers the destroy method
    }
}
```

#### Output
```
Bean is initialized using @PostConstruct annotation.
Bean is destroyed using @PreDestroy annotation.
```

---

## Summary of Approaches

| Approach          | Initialization Method          | Destruction Method          | Configuration Type |
|-------------------|--------------------------------|-----------------------------|--------------------|
| Declarative (XML) | `init-method` in XML           | `destroy-method` in XML     | XML                |
| Programmatic      | `InitializingBean.afterPropertiesSet()` | `DisposableBean.destroy()` | Java Class         |
| Annotation        | `@PostConstruct`               | `@PreDestroy`               | Java Class         |

---

## Key Takeaways

1. **Declarative Approach**:
    - Use `init-method` and `destroy-method` in the XML configuration.
    - Suitable for simple use cases.

2. **Programmatic Approach**:
    - Implement `InitializingBean` and `DisposableBean` interfaces.
    - Provides more control but tightly couples the bean to Spring interfaces.

3. **Annotation Approach**:
    - Use `@PostConstruct` and `@PreDestroy` annotations.
    - Clean and modern approach, but requires `javax.annotation` dependency.

4. **Choose the Right Approach**:
    - Use the declarative approach for simple configurations.
    - Use the annotation approach for modern, annotation-driven applications.
    - Use the programmatic approach when you need fine-grained control.

---

## Additional Notes

- Ensure that the `javax.annotation` dependency is included in your project for `@PostConstruct` and `@PreDestroy` annotations:
  ```xml
  <dependency>
      <groupId>javax.annotation</groupId>
      <artifactId>javax.annotation-api</artifactId>
      <version>1.3.2</version>
  </dependency>
  ```

- For XML-based configuration, use `<context:annotation-config/>` to enable annotation processing.

---