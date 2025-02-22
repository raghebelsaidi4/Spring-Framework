package com.ragheb.jdbc.repository.impl;

import com.ragheb.jdbc.entity.Employee;
import com.ragheb.jdbc.mapper.EmployeeRowMapper;
import com.ragheb.jdbc.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Repository
//@Slf4j
//@RequiredArgsConstructor
//@Qualifier("EmployeeJDBCRepository")
//public class EmployeeJDBCRepository implements EmployeeRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Override
//    public List<Employee> findByName(String name) {
//        return List.of();
//    }
//
//    @Override
//    public List<Employee> findBySalary(double salary) {
//        return List.of();
//    }
//
//    @Override
//    public List<Employee> findByNameStartingWithAndSalary(String name, double salary) {
//        return List.of();
//    }
//
//    @Override
//    public List<Employee> findByNameContainingAndSalaryGreaterThan(String name, double salary) {
//        return List.of();
//    }
//
//    @Override
//    public int update(Employee updatedEmployee) {
//        return 0;
//    }

//    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM employee";
//    private static final String FIND_BY_ID_QUERY = "SELECT * FROM employee WHERE id = ?";
//    private static final String FIND_ALL_QUERY = "SELECT * FROM employee";
//    private static final String INSERT_QUERY = "INSERT INTO employee(name, salary) VALUES (?, ?)";
//    private static final String UPDATE_QUERY = "UPDATE employee SET name = ?, salary = ? WHERE id = ?";
//    private static final String DELETE_QUERY = "DELETE FROM employee WHERE id = ?";
//
//    @Override
//    public long count() {
//        Integer count = jdbcTemplate.queryForObject(COUNT_QUERY, Integer.class);
//        //ensures that if count is null, it defaults to 0.
//        return Objects.requireNonNullElse(count, 0);
//    }
//
//    @Override
//    public Optional<Employee> findById(Long id) {
//        try {
//            return Optional.ofNullable(
//                    jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new EmployeeRowMapper(), id)
//            );
//        } catch (EmptyResultDataAccessException e) {
//            log.warn("Employee with id {} not found", id);
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    public List<Employee> findAll() {
//        return jdbcTemplate.query(FIND_ALL_QUERY, new EmployeeRowMapper());
//    }
//
//    @Override
//    public Object save(Employee employee) {
//        return jdbcTemplate.update(INSERT_QUERY, employee.getName(), employee.getSalary());
//    }
//
//    @Override
//    public int update(Employee employee) {
//        return jdbcTemplate.update(UPDATE_QUERY, employee.getName(), employee.getSalary(), employee.getId());
//    }
//
//    @Override
//    public int deleteById(Long id) {
//        return jdbcTemplate.update(DELETE_QUERY, id);
//    }
//}
