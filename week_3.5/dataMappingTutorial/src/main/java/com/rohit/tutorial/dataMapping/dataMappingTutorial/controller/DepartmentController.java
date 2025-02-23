package com.rohit.tutorial.dataMapping.dataMappingTutorial.controller;

import com.rohit.tutorial.dataMapping.dataMappingTutorial.entities.DepartmentEntity;
import com.rohit.tutorial.dataMapping.dataMappingTutorial.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{department}")
    public DepartmentEntity getDepartmentById(@PathVariable Long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity departmentEntity){
        return departmentService.createNewDepartment(departmentEntity);
    }

    @PutMapping("/{departmentId}/manager/{employeeId}")
    public DepartmentEntity assignManagerToDepartment(@PathVariable Long departmentId,
                                                      @PathVariable Long employeeId){
        return departmentService.assignManagerToDepartment(departmentId,employeeId);
    }

    @GetMapping("/assignedDepartmentOfManager/{employeeId}")
    public DepartmentEntity assignedDepartmentOfManager(@PathVariable Long employeeId){
        return departmentService.assignedDepartmentOfManager(employeeId);
    }

    @PutMapping("/{departmentId}/worker/{employeeId}")
    public DepartmentEntity assignWorkerToDepartment(@PathVariable Long departmentId,
                                                      @PathVariable Long employeeId){
        return departmentService.assignWorkerToDepartment(departmentId,employeeId);
    }

    @PutMapping("/{departmentId}/freelancer/{employeeId}")
    public DepartmentEntity assignFreelancersToDepartment(@PathVariable Long departmentId,
                                                          @PathVariable Long employeeId){
        return departmentService.assignFreelancersToDepartment(departmentId,employeeId);
    }
}
