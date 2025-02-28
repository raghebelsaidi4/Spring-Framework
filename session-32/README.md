# Unit Testing

Unit testing is the process of testing a small, isolated piece of code (a unit) to verify its correctness. It ensures that each function or method works as expected before integrating it with other components.

## Benefits of Unit Testing:
- Helps detect bugs early in development.
- Ensures code correctness and improves code quality.
- Makes code refactoring easier by verifying that existing functionality remains intact.
- Enhances maintainability and reliability of applications.

## JUnit: The Java Unit Testing Framework
JUnit is an open-source framework for performing unit testing in Java applications. It provides assertions, test runners, and annotations to make writing and running tests easy.

### Example of a Simple JUnit Test
```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Calculator {
    int add(int a, int b) {
        return a + b;
    }
}

public class CalculatorTest {
    @Test
    void testAddition() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3), "Addition method should return the correct sum");
    }
}
```

---

# Mocking

Mocking is the process of creating substitute objects for real objects to test a unit in isolation. Mocks are used when testing code that depends on external services, databases, or other dependencies that we want to simulate.

## Benefits of Mocking:
- Allows unit testing without requiring actual dependencies.
- Increases test speed and efficiency.
- Helps focus on the logic being tested rather than external components.
- Avoids unwanted side effects from real implementations.

## Mockito: The Java Mocking Framework
Mockito is a popular Java framework used for creating mock objects in unit tests. It allows us to mock dependencies and verify interactions.

### Example of Mocking with Mockito
```java
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserEmail(int userId) {
        return userRepository.findEmailById(userId);
    }
}

interface UserRepository {
    String findEmailById(int userId);
}

public class UserServiceTest {
    @Test
    void testGetUserEmail() {
        UserRepository mockRepo = mock(UserRepository.class);
        when(mockRepo.findEmailById(1)).thenReturn("test@example.com");

        UserService userService = new UserService(mockRepo);
        assertEquals("test@example.com", userService.getUserEmail(1));
    }
}
```

### More Examples of Unit Testing & Mocking
#### Testing a Service Layer with Mockito
```java
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PaymentService {
    private PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public boolean processPayment(double amount) {
        return paymentGateway.charge(amount);
    }
}

interface PaymentGateway {
    boolean charge(double amount);
}

public class PaymentServiceTest {
    @Test
    void testProcessPayment() {
        PaymentGateway mockGateway = mock(PaymentGateway.class);
        when(mockGateway.charge(100.0)).thenReturn(true);

        PaymentService paymentService = new PaymentService(mockGateway);
        assertTrue(paymentService.processPayment(100.0));
    }
}
```

#### Verifying Method Calls in Mockito
```java
@Test
void testVerifyMethodCall() {
    List<String> mockList = mock(List.class);
    mockList.add("Hello");

    verify(mockList).add("Hello"); // Verifies that add() was called with "Hello"
    verify(mockList, never()).clear(); // Verifies that clear() was never called
}
```

#### Mocking Exceptions
```java
@Test
void testMockException() {
    UserRepository mockRepo = mock(UserRepository.class);
    when(mockRepo.findEmailById(anyInt())).thenThrow(new RuntimeException("Database error"));
    
    assertThrows(RuntimeException.class, () -> mockRepo.findEmailById(1));
}
```

## Conclusion
Unit testing and mocking are essential practices in software development that improve code reliability, maintainability, and testability. JUnit and Mockito make it easier to implement effective unit tests for Java applications.
