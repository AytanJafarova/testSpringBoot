package com.example.demoTest.service;

import com.example.demoTest.entity.Department;
import com.example.demoTest.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository)
    {
        this.departmentRepository = departmentRepository;
    }

    // Adding new Department
    public Department addDepartment(Department department)
    {
        try{
            return departmentRepository.save(department);
        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Getting all Departments
    public List<Department> getAllDepartments()
    {
        try
        {
            return departmentRepository.findAll();
        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Get Department by ID
    public Optional<Department> getDepartmentById(Long id)
    {
        try{
            return departmentRepository.findById(id);
        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Update Department by ID
    public Optional<Department> updateDepartment(Long id, Department department)
    {
        try
        {
            Optional<Department> selectedDepartment = departmentRepository.findById(id);
            if (selectedDepartment.isPresent()) {
                Department selected = selectedDepartment.get();
                selected.setName(department.getName());
                Department savedDepartment = departmentRepository.save(selected);
                return Optional.of(savedDepartment);
            }
            else {
                return Optional.empty();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }


    // Deleting department based on ID
    public boolean deleteDepartment(Long id) {
        try {
            departmentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
