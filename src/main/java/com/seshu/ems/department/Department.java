package com.seshu.ems.department;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seshu.ems.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    // One-to-many relationship with Employee
    @OneToMany(
            mappedBy = "department",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties({"department", "address"})
    private List<Employee> employee = new ArrayList<>();
}
