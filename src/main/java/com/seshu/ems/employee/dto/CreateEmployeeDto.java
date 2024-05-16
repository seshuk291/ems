package com.seshu.ems.employee.dto;

import com.seshu.ems.address.dto.CreateAddressDto;

public record CreateEmployeeDto(
       String name,
       String phone,
       CreateAddressDto address,
       Long departmentId,
       String position
) { }
