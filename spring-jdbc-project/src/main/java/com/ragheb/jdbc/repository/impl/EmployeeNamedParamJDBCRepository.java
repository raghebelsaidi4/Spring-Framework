package com.ragheb.jdbc.repository.impl;

import com.ragheb.jdbc.entity.Employee;
import com.ragheb.jdbc.mapper.EmployeeRowMapper;
import com.ragheb.jdbc.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Repository
//@RequiredArgsConstructor
//@Qualifier("EmployeeNamedParamJDBCRepository")
//@Primary
//public class EmployeeNamedParamJDBCRepository implements EmployeeRepository {
//
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    @Override
//    public long count() {
//        String sql = "SELECT COUNT(*) FROM `spring-jdbc`.employee";
//        Integer count = namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
//        return (count != null) ? count : 0;
//    }
//
//    @Override
//    public Optional<Employee> findById(Long id) {
//        String sql = "SELECT * FROM `spring-jdbc`.employee WHERE id = :id";
//        Map<String, Object> params = Map.of("id", id);
//        try {
//            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, params, new EmployeeRowMapper()));
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    public List<Employee> findAll() {
//        String sql = "SELECT * FROM `spring-jdbc`.employee";
//        return namedParameterJdbcTemplate.query(sql, new EmployeeRowMapper());
//    }
//
//    @Override
//    public Object save(Employee employee) {
//        String sql = "INSERT INTO `spring-jdbc`.employee(name, salary) VALUES (:name, :salary)";
//        Map<String, Object> params = Map.of(
//                "name", employee.getName(),
//                "salary", employee.getSalary()
//        );
//        return namedParameterJdbcTemplate.update(sql, params);
//    }
//
//    @Override
//    public int update(Employee employee) {
//        String sql = "UPDATE `spring-jdbc`.employee SET name = :name, salary = :salary WHERE id = :id";
//        Map<String, Object> params = Map.of(
//                "id", employee.getId(),
//                "name", employee.getName(),
//                "salary", employee.getSalary()
//        );
//        return namedParameterJdbcTemplate.update(sql, params);
//    }
//
//    @Override
//    public int deleteById(Long id) {
//        String sql = "DELETE FROM `spring-jdbc`.employee WHEREid = :id";
//        Map<String, Object> params = Map.of("id", id);
//        return namedParameterJdbcTemplate.update(sql, params);
//    }
//}
