package com.seshu.ems.employee.dto;

import com.seshu.ems.address.Address;

public record UpdateEmployeeDto(
            String name,
            String phone,
            Address address,
            Long departmentId,
            String position
) {
}
