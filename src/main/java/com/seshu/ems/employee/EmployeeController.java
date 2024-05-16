package com.seshu.ems.employee;

import com.seshu.ems.employee.dto.CreateEmployeeDto;
import com.seshu.ems.employee.dto.UpdateEmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("")
    public ResponseEntity<Employee> createEmployee(@RequestBody CreateEmployeeDto createEmployeeDto) {
        Employee createdEmployee = this.employeeService.createEmployee(createEmployeeDto);
        return ResponseEntity.status(200).body(createdEmployee);
    }

    @GetMapping("")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = this.employeeService.getAllEmployees();
        return ResponseEntity.status(200).body(employeeList);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody UpdateEmployeeDto updateEmployeeDto, @PathVariable("empId") Long empId) {
        Employee updatedEmployee = this.employeeService.updateEmployee(updateEmployeeDto, empId);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("empId") Long empId) {
        this.employeeService.deleteEmployee(empId);
        return ResponseEntity.ok("Employee deleted");
    }

}
