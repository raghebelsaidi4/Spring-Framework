# Spring IOC (Inversion of Control) Example Project

## Overview

This project demonstrates the use of Spring's Inversion of Control (IoC) container to manage dependencies between objects. The IoC principle is a design pattern in which the control flow of a program is inverted: instead of the programmer controlling the flow, the framework (in this case, Spring) takes control. This is achieved through Dependency Injection (DI), where the dependencies of a class are injected by the framework rather than the class creating them itself.

## Importance of IoC

Inversion of Control is a key principle in modern software development, particularly in enterprise applications. Here are some of the benefits:

1. **Decoupling**: IoC helps in decoupling the execution of a task from its implementation. This makes the code more modular and easier to maintain.
2. **Testability**: With IoC, dependencies can be easily mocked or stubbed, making unit testing simpler and more effective.
3. **Flexibility**: By externalizing the configuration of dependencies, the application becomes more flexible. You can easily switch implementations without changing the code.
4. **Reusability**: Components become more reusable as they are not tightly coupled to specific implementations of their dependencies.
5. **Simplified Code**: IoC reduces boilerplate code, as the framework handles the instantiation and wiring of objects.

## Project Description

This project consists of a simple payment processing system that demonstrates how Spring IoC can be used to manage dependencies between different payment methods (Credit Card and Debit Card) and a payment service.

### Components

1. **IPayment Interface**:
    - This interface defines a contract for processing payments. It has a single method `precessPayment(double billAmount)` which returns a boolean indicating the success or failure of the payment.

2. **CreditCardPayment Class**:
    - Implements the `IPayment` interface and provides the logic for processing payments via a credit card.

3. **DebitCardPayment Class**:
    - Implements the `IPayment` interface and provides the logic for processing payments via a debit card.

4. **PaymentService Class**:
    - This class is responsible for executing the payment process. It has a dependency on the `IPayment` interface, which is injected via constructor or setter injection. The `doPayment(double billAmount)` method uses the injected `IPayment` implementation to process the payment.

5. **App Class**:
    - The main application class that initializes the Spring IoC container, retrieves the `PaymentService` bean, and invokes the `doPayment` method.

6. **beans.xml**:
    - This is the Spring configuration file where the beans are defined. It specifies how the `PaymentService` bean should be instantiated and how its dependencies (CreditCardPayment and DebitCardPayment) should be injected.

### How It Works

1. **Spring Configuration**:
    - In `beans.xml`, we define three beans:
        - `credit`: An instance of `CreditCardPayment`.
        - `debit`: An instance of `DebitCardPayment`.
        - `payment`: An instance of `PaymentService` with `CreditCardPayment` injected via constructor and `DebitCardPayment` injected via setter.

2. **Dependency Injection**:
    - When the `PaymentService` bean is created, Spring injects the `CreditCardPayment` bean via the constructor and then injects the `DebitCardPayment` bean via the setter method. This demonstrates both constructor and setter injection.

3. **Execution**:
    - The `App` class initializes the Spring context, retrieves the `PaymentService` bean, and calls the `doPayment` method. The payment is processed using the injected `IPayment` implementation.

### Output

When you run the application, you should see the following output:

```
CreditCardPayment Constructor
DebitCardPayment constructor
PaymentService constructor
setPayment method called...
Precess Payment of Debit Card successful...
Payment successful
```

This output shows:
- The `CreditCardPayment` and `DebitCardPayment` beans are instantiated.
- The `PaymentService` bean is created, and the `DebitCardPayment` bean is injected via the setter method.
- The payment is processed successfully using the `DebitCardPayment` implementation.


## Dependencies

- Spring Context: `6.2.2`
- Spring Core: `6.2.1`
- Spring Beans: `6.2.2`

These dependencies are specified in the `pom.xml` file.

---

