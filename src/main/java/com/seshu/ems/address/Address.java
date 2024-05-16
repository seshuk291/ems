package com.seshu.ems.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seshu.ems.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String address1;
    private String address2;
    private String pincode;
    private String city;
    private String state;
    private String country;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties({"address", "leave", "department"})
    private Employee employee;

}
