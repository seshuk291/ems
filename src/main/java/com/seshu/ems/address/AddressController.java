package com.seshu.ems.address;
import com.seshu.ems.address.dto.CreateAddressDto;
import com.seshu.ems.address.dto.UpdateAddressDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;
    AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{empId}")
    public ResponseEntity<List<Address>> getAllAddressByEmpId(@PathVariable("empId") Long empId) {
        List<Address> address = this.addressService.getAllAddressByEmpId(empId);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable("addressId") Long addressId) {
        Address address = this.addressService.getAddressById(addressId);
        return ResponseEntity.ok(address);
    }

    @PostMapping()
    public ResponseEntity<Address> createAddress(@RequestBody CreateAddressDto createAddressDto) {
        Address address = this.addressService.createAddress(createAddressDto);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updatedAddress(@RequestBody UpdateAddressDto updateAddressDto, @PathVariable("id") Long id) {
        Address address = this.addressService.updateAddress(updateAddressDto, id);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("addressId") Long id) {
        this.addressService.deleteAddress(id);
        return ResponseEntity.ok("Address deleted");
    }


}
