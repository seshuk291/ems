package com.seshu.ems.address;

import com.seshu.ems.address.dto.AddressDto;
import com.seshu.ems.address.dto.CreateAddressDto;
import com.seshu.ems.address.dto.UpdateAddressDto;
import com.seshu.ems.custom_exceptions.ResourceNotFoundException;
import com.seshu.ems.employee.Employee;
import com.seshu.ems.employee.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;

    AddressService(AddressRepository addressRepository, EmployeeRepository employeeRepository){
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }

    public Address getAddressById(Long addressId) {
        return this.addressRepository
                .findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address Not found"));
    }

    public List<Address> getAllAddressByEmpId(Long empId) {
        return this.addressRepository
                .findAddressByEmployeeId(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not Found"));
    }

    public Address createAddress(CreateAddressDto createAddressDto) {
        Employee existingEmployee = this.employeeRepository
                .findById(createAddressDto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found"));
        Address address = updateAddressFields(createAddressDto);
        address.setEmployee(existingEmployee);
        return this.addressRepository.save(address);
    }

    public Address updateAddress(UpdateAddressDto updateAddressDto, Long id) {
        //check if the address is there in the db
        Address updatedAddress = this.addressRepository.findById(id)
                .map(address -> updateAddressFields(updateAddressDto))
                .orElseThrow(() -> new ResourceNotFoundException("Address Not Found"));
        // update the address
        return this.addressRepository.save(updatedAddress);
    }

    public void deleteAddress(Long addressId) {
        this.addressRepository.deleteById(addressId);
    }

    public Address updateAddressFields(AddressDto addressDto) {
        return Address.builder()
                .address1(addressDto.getAddress1())
                .address2(addressDto.getAddress2())
                .pincode(addressDto.getPincode())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .country(addressDto.getCountry())
                .build();
    }
}