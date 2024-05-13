package com.seshu.ems.leave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seshu.ems.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "leaves")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties({"department", "leave", "address"})
    private Employee employee;

    private Date startDate;
    private Date endDate;
    private String reason;
}
