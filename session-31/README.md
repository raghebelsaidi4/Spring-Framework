# Spring Security Overview

Spring Security is a powerful framework that provides authentication, authorization, and protection against common security vulnerabilities for Java applications.

## Adding Spring Security to a Spring Boot Application

To use Spring Security, add the following dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

By default, Spring Security secures all endpoints and provides a default username and password:
- Default username: `user`
- Default password: Printed on the console at application startup.

## Customizing Username and Password

To override the default credentials, configure them in `application.properties`:

```properties
spring.security.user.name=admin
spring.security.user.password=admin@123
```

## Basic Authentication Example

A simple Spring Boot application with security enabled:

```java
@SpringBootApplication
public class SecurityDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
    }
}
```

## Configuring Security with Java Configuration

To define custom security configurations, create a `SecurityConfig` class:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(withDefaults())
            .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin@123")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
```

### Explanation:
- `@EnableWebSecurity`: Enables Spring Security.
- `securityFilterChain()`: Defines security rules:
  - `/public` endpoint is accessible to everyone.
  - All other requests require authentication.
  - Supports both form-based and basic authentication.
- `userDetailsService()`: Creates an in-memory user with credentials.

## Role-Based Access Control (RBAC)

To restrict access based on roles:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(withDefaults())
            .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin@123")
            .roles("ADMIN")
            .build();

        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("user@123")
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
```

### Explanation:
- `/admin` is accessible only to `ADMIN` users.
- `/user` is accessible to both `USER` and `ADMIN` roles.
- Defines two users with different roles.

## JWT Authentication

To use JWT-based authentication, add the following dependencies:

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.11.5</version>
</dependency>
```

### JWT Security Configuration

```java
@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}
```

## Conclusion

Spring Security provides robust authentication and authorization mechanisms. From basic authentication to JWT-based security, it helps developers secure their applications efficiently.
