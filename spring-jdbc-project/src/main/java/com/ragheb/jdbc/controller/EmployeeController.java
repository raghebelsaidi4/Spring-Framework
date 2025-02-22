package com.ragheb.jdbc.controller;

import com.ragheb.jdbc.entity.Employee;
import com.ragheb.jdbc.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/count")
    public ResponseEntity<Long> getEmployeeCount() {
        long count = employeeRepository.count();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        Employee emp = employeeRepository.save(employee);
        return ResponseEntity.ok("Employee added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        // Ensure the provided employee object has the correct ID
        Employee updatedEmployee = new Employee(id, employee.getName(), employee.getSalary());
        // Use save() instead of update()
        employeeRepository.save(updatedEmployee);
        return ResponseEntity.ok("Employee updated successfully");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<Employee>> getEmployeesByName(@PathVariable String name) {
        List<Employee> employees = employeeRepository.findByName(name);
        return employees.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(employees);
    }

    @GetMapping("/by-salary/{salary}")
    public ResponseEntity<List<Employee>> getEmployeesBySalary(@PathVariable double salary) {
        List<Employee> employees = employeeRepository.findBySalary(salary);
        return employees.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(employees);
    }

    @GetMapping("/by-name-starting/{name}/salary/{salary}")
    public ResponseEntity<List<Employee>> getEmployeesByNameStartingWithAndSalary(
            @PathVariable String name, @PathVariable double salary) {
        List<Employee> employees = employeeRepository.findByNameStartingWithAndSalary(name, salary);
        return employees.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(employees);
    }

    @GetMapping("/search/{name}/greater-than/{salary}")
    public ResponseEntity<List<Employee>> getEmployeesByNameContainingAndSalaryGreaterThan(
            @PathVariable String name, @PathVariable double salary) {
        List<Employee> employees = employeeRepository.findByNameContainingAndSalaryGreaterThan(name, salary);
        return employees.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(employees);
    }
}
