package com.seshu.ems.department;

import com.seshu.ems.custom_exceptions.ResourceNotFoundException;
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

    public Department updateDepartment(Department department, Long departmentId) {

        Department existingDepartment = this.departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not Found"));

        Department departmentToUpdate = Department
                .builder()
                .name(department.getName())
                .description(department.getDescription())
                .id(existingDepartment.getId())
                .build();

        return this.departmentRepository.save(departmentToUpdate);
    }

    public Department getDepartmentById(Long departmentId) {
        return this.departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Department with id: %d Not Found", departmentId)));
    }

    public void deleteDepartment(Long departmentId) {
        this.departmentRepository.deleteById(departmentId);
    }
}
