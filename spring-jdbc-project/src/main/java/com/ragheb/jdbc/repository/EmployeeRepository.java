package com.ragheb.jdbc.repository;

import com.ragheb.jdbc.entity.Employee;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
//    public long count();
//    public Optional<Employee> findById(Long id);
//    public List<Employee> findAll();
//    public Employee save(Employee employee);
//    public int update(Employee employee);
//    public void deleteById(Long id);
    List<Employee> findByName(String name);
    List<Employee> findBySalary(double salary);
    List<Employee> findByNameStartingWithAndSalary(String name, double salary);

    @Query(value = "select * from employee where name like :empName and salary > :empSalary")
    List<Employee> findByNameContainingAndSalaryGreaterThan(@Param("empName") String name, @Param("empSalary") double salary);

}
