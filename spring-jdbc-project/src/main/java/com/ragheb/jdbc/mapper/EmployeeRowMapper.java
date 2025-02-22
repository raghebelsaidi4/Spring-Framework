package com.ragheb.jdbc.mapper;

import com.ragheb.jdbc.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(rs.getLong("id"), rs.getString("name"), rs.getDouble("salary"));
    }
}
