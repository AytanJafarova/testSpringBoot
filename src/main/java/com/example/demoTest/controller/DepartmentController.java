package com.example.demoTest.controller;

import com.example.demoTest.entity.Department;
import com.example.demoTest.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService)
    {
        this.departmentService = departmentService;
    }

    // Adding new Department
    @PostMapping("/department")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department)
    {
        return ResponseEntity.ok(departmentService.addDepartment(department));
    }

    // Get all Departments
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments()
    {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }


    // Get Department by ID
    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentByID(@PathVariable Long id) {
        return departmentService.getDepartmentById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a product
    @PutMapping(path = "/department/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        return departmentService.updateDepartment(id, department).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a Department
    @DeleteMapping(value = "/department/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        boolean deletionStatus = departmentService.deleteDepartment(id);
        if (deletionStatus) {
            return ResponseEntity.ok("Department with ID " + id + " has been deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete department with ID " + id);
        }
    }


}
