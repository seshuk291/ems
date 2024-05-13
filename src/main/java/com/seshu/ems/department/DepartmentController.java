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

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getAllDepartments(@PathVariable("departmentId") Long departmentId) {
        Department department = this.departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(department);
    }

    @PostMapping("")
    public ResponseEntity<Department> createDepartment(@RequestBody CreateDepartmentDTO createDepartmentDTO) {
        Department department = this.departmentService.createDepartment(createDepartmentDTO);
        return ResponseEntity.ok(department);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department, @PathVariable("departmentId") Long departmentId) {
        Department updatedDepartment = this.departmentService.updateDepartment(department, departmentId);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("departmentId") Long departmentId) {
        this.departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok("Department deleted");
    }

}
