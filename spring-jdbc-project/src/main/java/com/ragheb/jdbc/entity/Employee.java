package com.ragheb.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id @Column("id")
    private Long employeeId;
    private String name;
    private Double salary;
    @Transient
    private boolean isCreated;

    public Employee(Long employeeId, String name, Double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }
}


