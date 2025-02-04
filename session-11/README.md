# JpaRepository

## Overview
`JpaRepository` is a predefined interface available in Spring Data JPA. It provides methods to perform CRUD (Create, Read, Update, Delete) operations, as well as additional functionalities like sorting, pagination, and Query by Example (QBE).

### Key Features:
- **CRUD Operations**: Basic operations to manage entities.
- **Sorting**: Sort query results based on specific fields.
- **Pagination**: Divide large datasets into manageable pages.
- **Query by Example (QBE)**: Execute queries based on example entities.

### Example Usage:
```java
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can be defined here
    List<User> findByLastName(String lastName);
}
```

## Pagination
Pagination is the process of dividing a large set of records into smaller, more manageable pages. This is particularly useful when dealing with large datasets.

### Example:
```java
Pageable pageable = PageRequest.of(0, 10); // Page 1 with 10 records per page
Page<User> users = userRepository.findAll(pageable);
```

## Timestamping in JPA
Timestamping is used to automatically populate `CREATE_DATE` and `UPDATE_DATE` columns in the database.

### Annotations:
- `@CreationTimestamp`: Automatically populates the creation date of the record.
- `@UpdateTimestamp`: Automatically updates the modification date of the record.

### Example:
```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    // Getters and Setters
}
```

## Generators
Generators are used to generate values for primary key columns. A primary key (PK) is a constraint in the database used to maintain unique data in a column. It combines two constraints:
- **Unique**: Ensures all values in the column are unique.
- **Not Null**: Ensures no null values are present.

### Strategies for Generators:
1. **Auto**: The persistence provider chooses the appropriate strategy.
2. **Identity**: Used in MySQL, where the database generates the primary key.
3. **Sequence**: Used in Oracle, where a database sequence generates the primary key.
4. **Table**: Uses a separate table to generate primary key values.

### Example:
```java
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    // Getters and Setters
}

```
