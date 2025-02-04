## Spring Data JPA
Spring Data JPA is a module of the Spring framework used to develop the **persistence layer (DB logic)** and simplify CRUD operations.

### Evolution of Data Persistence
1. **Java JDBC**
2. **Spring JDBC**
3. **Hibernate Framework**
4. **Spring ORM**
5. **Spring Data JPA**

If an application has **5000 tables**, we would need **5000 DAO classes** with at least **4 common methods**, resulting in **20,000 redundant methods**. To avoid this boilerplate code, Spring Data JPA provides repository interfaces that simplify persistence logic using Hibernate internally.

### Spring Data JPA Repositories
Spring Data JPA provides repository interfaces for easier persistence layer development:

#### 1. `CrudRepository<T, ID>` (Basic CRUD operations)
- `save(E entity)`, `saveAll(Iterable<E> entities)`
- `existsById(ID id)`, `count()`
- `findById(ID id)`, `findAll()`, `findAllById(Iterable<ID> ids)`
- `delete(E entity)`, `deleteAll()`, `deleteAllById(Iterable<ID> ids)`

#### 2. `JpaRepository<T, ID>` (Extends `CrudRepository` with sorting, pagination, and Query by Example (QBE))

### Developing a Spring Data JPA App
1. Create a Spring Boot project with dependencies:
    - `spring-boot-starter-data-jpa`
    - `mysql-connector-java`
    - `lombok`
2. Configure `application.properties` for the database connection.
3. Create an Entity class mapping Java objects to DB tables.
4. Create a repository interface extending `JpaRepository`.
5. Test the repository methods.

### FindBy Methods
`findBy` methods are used for **select operations** on non-primary key columns. The method name is crucial as JPA constructs queries based on it.

### Custom Queries (`@Query` Annotation)
For executing custom queries, we can use:
- **HQL (Hibernate Query Language)** – DB-independent, uses entity class names and variables.
- **SQL (Native Queries)** – DB-dependent, uses table and column names.

**Example:**
```java
@Query("SELECT b FROM Book b WHERE b.price = :price")
List<Book> findBooksByPrice(@Param("price") double price);
```

### Dialect Class
Every HQL query is converted to SQL by the **Dialect class**, which is specific to each database:
- MySQL: `org.hibernate.dialect.MySQLDialect`
- PostgreSQL: `org.hibernate.dialect.PostgreSQLDialect`
- Oracle: `org.hibernate.dialect.OracleDialect`

---

