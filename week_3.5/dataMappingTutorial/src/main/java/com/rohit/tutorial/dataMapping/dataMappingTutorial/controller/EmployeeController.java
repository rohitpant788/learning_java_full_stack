package com.rohit.tutorial.dataMapping.dataMappingTutorial.controller;

import com.rohit.tutorial.dataMapping.dataMappingTutorial.entities.EmployeeEntity;
import com.rohit.tutorial.dataMapping.dataMappingTutorial.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity) {
        return employeeService.createNewEmployee(employeeEntity);
    }
}
