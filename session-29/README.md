# Mono & Flux in Project Reactor

## Introduction

Project Reactor is a reactive programming library for building non-blocking applications using the Reactive Streams specification.
It provides two main types:
- **Mono**: Represents a single value or an empty result.
- **Flux**: Represents a sequence of multiple values.

## Dependencies
To use Mono and Flux, include the following dependency in your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>io.projectreactor</groupId>
        <artifactId>reactor-core</artifactId>
        <version>3.5.0</version>
    </dependency>
</dependencies>
```

## Mono Example
A **Mono** can be created using `Mono.just()`, `Mono.empty()`, or `Mono.fromSupplier()`.
Here's an example:

```java
import reactor.core.publisher.Mono;

public class MonoExample {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello, Mono!");
        
        mono.subscribe(System.out::println, // onNext
                       Throwable::printStackTrace, // onError
                       () -> System.out.println("Mono completed!")); // onComplete
    }
}
```

## Flux Example
A **Flux** can emit multiple values using `Flux.just()`, `Flux.fromIterable()`, or `Flux.range()`.

```java
import reactor.core.publisher.Flux;

public class FluxExample {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("A", "B", "C", "D");
        
        flux.subscribe(System.out::println, // onNext
                       Throwable::printStackTrace, // onError
                       () -> System.out.println("Flux completed!")); // onComplete
    }
}
```

## Handling Errors
Both **Mono** and **Flux** support error handling using `onErrorReturn()`, `onErrorResume()`, or `doOnError()`.

```java
Mono<String> monoWithError = Mono.error(new RuntimeException("Something went wrong"))
        .onErrorReturn("Fallback value");
        
monoWithError.subscribe(System.out::println);
```

## Transforming Data
Use `map()` and `flatMap()` to transform data within a **Mono** or **Flux**.

```java
Flux<Integer> numbers = Flux.range(1, 5).map(n -> n * 2);
numbers.subscribe(System.out::println);
```

## Conclusion
- **Mono** is for a single value.
- **Flux** is for multiple values.
- Use error handling mechanisms for resilience.
- Transform data using `map()` and `flatMap()`.
