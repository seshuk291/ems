package com.seshu.ems.employee;

import com.seshu.ems.address.Address;
import com.seshu.ems.address.AddressRepository;
import com.seshu.ems.address.AddressService;
import com.seshu.ems.custom_exceptions.ResourceNotFoundException;
import com.seshu.ems.department.Department;
import com.seshu.ems.department.DepartmentRepository;
import com.seshu.ems.employee.dto.CreateEmployeeDto;
import com.seshu.ems.employee.dto.UpdateEmployeeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final AddressRepository addressRepository;
    private final AddressService addressService;


    EmployeeService(EmployeeRepository employeeRepository,
                    DepartmentRepository departmentRepository,
                    AddressRepository addressRepository,
                    AddressService addressService
    ) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.addressRepository = addressRepository;
        this.addressService = addressService;
    }

    @Transactional
    public Employee createEmployee(CreateEmployeeDto employeeDto) {
       Employee employee =  Employee.builder()
                .name(employeeDto.name())
                .phone(employeeDto.phone())
                .position(employeeDto.position())
                .build();

       this.setEmployeeDepartment(employee, employeeDto.departmentId());
       Employee createdEmployee = this.employeeRepository.save(employee);
       employeeDto.address().setEmployeeId(createdEmployee.getId());
       Address createdAddress = this.addressService.createAddress(employeeDto.address());
       employee.setAddress(List.of(createdAddress));

       return  employee;
    }

    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    public void setEmployeeDepartment(Employee employee, Long departmentId) {
        Department department = this.departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not Found"));
        employee.setDepartment(department);
    }

    @Transactional
    public Employee updateEmployee(UpdateEmployeeDto updateEmployeeDto, Long empId) {
        Employee existingEmployee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found"));
        Employee employeeToUpdate = Employee
                .builder()
                .id(existingEmployee.getId())
                .phone(updateEmployeeDto.phone())
                .name(updateEmployeeDto.name())
                .position(updateEmployeeDto.position())
                .build();
        this.setEmployeeDepartment(employeeToUpdate, updateEmployeeDto.departmentId());
        return this.employeeRepository.save(employeeToUpdate);
    }

    public void deleteEmployee(Long empId) {
        this.employeeRepository.deleteById(empId);
    }
}
