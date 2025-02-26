# Spring Boot with Redis Integration

## **1. Introduction to Caching**
**Cache** is a temporary storage area that holds data temporarily. When an application frequently accesses the same data, caching improves performance by reducing database calls.

### **Why Use Cache?**
- **Improves Performance**: Reduces latency by avoiding frequent database queries.
- **Reduces Database Load**: Minimizes expensive database round trips.
- **Enhances Scalability**: Ensures smooth operation under high traffic.

## **2. Introduction to Redis Cache**
**Redis** is an open-source, in-memory data store used as a database, cache, message broker, and streaming engine. It stores data in **key-value** pairs and supports multiple applications accessing it simultaneously.

### **Key Features of Redis**
- **Fast and Scalable**: Provides sub-millisecond latency.
- **Supports Expiry Time**: Automatically removes outdated data.
- **Distributed System**: Allows multiple applications to access the cache.
- **Persistence**: Can be used as both a cache and a durable database.

## **3. Setting Up Redis in Spring Boot**

### **Step 1: Add Redis Dependency in `pom.xml`**
```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Cache -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>

    <!-- Redis Cache Dependency -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
</dependencies>
```

### **Step 2: Configure Redis in `application.yml`**
```yaml
spring:
  cache:
    type: redis
  redis:
    host: localhost
    port: 6379
```

### **Step 3: Enable Caching in Spring Boot**
Add `@EnableCaching` annotation in the main class.

```java
@SpringBootApplication
@EnableCaching
public class RedisCacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisCacheApplication.class, args);
    }
}
```

### **Step 4: Implement Caching in a Service Layer**

```java
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
```

### **Step 5: Create REST Controller**
```java
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted and removed from cache");
    }
}
```

### **Step 6: Run Redis Server Locally**
If Redis is not installed, use Docker:
```sh
docker run --name redis -d -p 6379:6379 redis
```

Or install manually from [Redis Official Site](https://redis.io/download/).

### **Step 7: Test Redis Cache**
1. **Call API to Fetch Product:**
   ```sh
   curl http://localhost:8080/products/1
   ```
    - The first call fetches data from the database and stores it in Redis.

2. **Call API Again for the Same Product:**
   ```sh
   curl http://localhost:8080/products/1
   ```
    - The second call fetches data from Redis instead of the database.

3. **Delete Product and Remove from Cache:**
   ```sh
   curl -X DELETE http://localhost:8080/products/1
   ```
    - This will remove the product from both Redis and the database.

## **4. Monitoring Redis Cache**
To monitor stored keys in Redis, use:
```sh
redis-cli
keys *
```
To get a specific cached value:
```sh
get products::1
```

## **5. Summary**
✅ Implemented Redis cache in Spring Boot
✅ Configured Redis properties in `application.yml`
✅ Used `@Cacheable` for caching and `@CacheEvict` for cache removal
✅ Tested caching mechanism and monitored Redis storage

By integrating Redis, we significantly **improve application performance** and **reduce database load**.

