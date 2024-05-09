package com.seshu.ems.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seshu.ems.address.Address;
import com.seshu.ems.department.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
    @JsonIgnoreProperties("employee")
    private List<Address> address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties("employee")
    private Department department;
}
