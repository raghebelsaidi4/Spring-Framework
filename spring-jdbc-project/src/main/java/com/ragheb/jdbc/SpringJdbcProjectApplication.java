package com.ragheb.jdbc;

import com.ragheb.jdbc.entity.Employee;
import com.ragheb.jdbc.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class SpringJdbcProjectApplication implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final EmployeeRepository employeeRepository;

    private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS employee";
    private static final String CREATE_TABLE_SQL = """
            CREATE TABLE employee (
                id BIGINT AUTO_INCREMENT,
                name VARCHAR(255),
                salary DOUBLE,
                PRIMARY KEY (id)
            )""";

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcProjectApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {
        try {
            log.info("Initializing database...");
            jdbcTemplate.execute(DROP_TABLE_SQL);
            jdbcTemplate.execute(CREATE_TABLE_SQL);

            if (employeeRepository.count() == 0) {
                log.info("Seeding database with initial employees...");
                List<Employee> employees = List.of(
                        new Employee(null, "Ragheb", 10000.0),
                        new Employee(null, "Ahmed", 20000.0),
                        new Employee(null, "Mohamed", 30000.0)
                );

                batchInsert(employees);
                log.info("Database initialization completed.");
            } else {
                log.info("Employees already exist. Skipping initialization.");
            }
        } catch (Exception e) {
            log.error("Error initializing database: {}", e.getMessage(), e);
        }
    }

    private void batchInsert(List<Employee> employees) {
        jdbcTemplate.batchUpdate(
                "INSERT INTO employee (name, salary) VALUES (?, ?)",
                employees,
                employees.size(),
                (ps, employee) -> {
                    ps.setString(1, employee.getName());
                    ps.setDouble(2, employee.getSalary());
                }
        );
    }
}

