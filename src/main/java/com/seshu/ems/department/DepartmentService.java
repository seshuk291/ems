package com.seshu.ems.department;

import com.seshu.ems.department.dto.CreateDepartmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return this.departmentRepository.findAll();
    }

    public Department createDepartment(CreateDepartmentDTO createDepartmentDTO) {
        Department department = Department.builder()
                .name(createDepartmentDTO.name())
                .description(createDepartmentDTO.description())
                .build();
        return this.departmentRepository.save(department);
    }
}
