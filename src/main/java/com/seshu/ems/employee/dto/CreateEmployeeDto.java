package com.seshu.ems.employee.dto;

import com.seshu.ems.address.Address;
import com.seshu.ems.department.Department;

public record CreateEmployeeDto(
       String name,
       String phone,
       Address address,
       Long departmentId
) { }
