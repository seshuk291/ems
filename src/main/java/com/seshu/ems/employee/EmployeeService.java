package com.seshu.ems.employee;

import com.seshu.ems.address.Address;
import com.seshu.ems.address.AddressRepository;
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
    private final AddressRepository addressRepository;

    EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.addressRepository = addressRepository;
    }

    public Employee createEmployee(CreateEmployeeDto employeeDto) {
       Employee employee =  Employee.builder()
                .name(employeeDto.name())
                .phone(employeeDto.phone())
                .build();
//       List<Address> addressesList = new ArrayList<>();
//       addressesList.add(address);
//       employee.setAddress(addressesList);
       this.setEmployeeDepartment(employee, employeeDto.departmentId());
       Employee createdEmployee = this.employeeRepository.save(employee);
       Address address = Address.builder()
               .address1(employeeDto.address().getAddress1())
               .address2(employeeDto.address().getAddress2())
               .city(employeeDto.address().getCity())
               .state(employeeDto.address().getState())
               .country(employeeDto.address().getCountry())
               .pincode(employeeDto.address().getPincode())
               .build();
       address.setEmployee(createdEmployee);
       Address createdAddress = this.addressRepository.save(address);
       employee.setAddress(List.of(createdAddress));
       return  employee;
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
