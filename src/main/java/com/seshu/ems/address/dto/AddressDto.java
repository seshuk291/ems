package com.seshu.ems.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto{
    private String address1;
    private String address2;
    private String pincode;
    private String city;
    private String state;
    private String country;
    private Long employeeId;
}
