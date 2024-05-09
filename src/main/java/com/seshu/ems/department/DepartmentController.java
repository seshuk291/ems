package com.seshu.ems.department;

import com.seshu.ems.department.dto.CreateDepartmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping("")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departmentList = this.departmentService.getAllDepartments();
        return ResponseEntity.ok(departmentList);
    }

    @PostMapping("")
    public ResponseEntity<Department> createDepartment(@RequestBody CreateDepartmentDTO createDepartmentDTO) {
        Department department = this.departmentService.createDepartment(createDepartmentDTO);
        return ResponseEntity.ok(department);
    }
}
