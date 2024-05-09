package com.seshu.ems.employee;

import com.seshu.ems.address.Address;
import com.seshu.ems.department.Department;
import com.seshu.ems.department.DepartmentRepository;
import com.seshu.ems.employee.dto.CreateEmployeeDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Employee createEmployee(CreateEmployeeDto employeeDto) {
        Address address = Address.builder()
                .address1(employeeDto.address().getAddress1())
                .address2(employeeDto.address().getAddress2())
                .city(employeeDto.address().getCity())
                .state(employeeDto.address().getState())
                .country(employeeDto.address().getCountry())
                .pincode(employeeDto.address().getPincode())
                .build();
       Employee employee =  Employee.builder()
                .name(employeeDto.name())
                .phone(employeeDto.phone())
                .build();
       List<Address> addressesList = new ArrayList<>();
       addressesList.add(address);
       employee.setAddress(addressesList);
       this.setEmployeeDepartment(employee, employeeDto.departmentId());
       return this.employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    public Employee setEmployeeDepartment(Employee employee, Long departmentId) {
        Department department = this.departmentRepository.findById(departmentId).orElseThrow();
        employee.setDepartment(department);
        return employee;
    }
}
