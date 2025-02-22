package com.rohit.tutorial.dataMapping.dataMappingTutorial.services;

import com.rohit.tutorial.dataMapping.dataMappingTutorial.entities.DepartmentEntity;
import com.rohit.tutorial.dataMapping.dataMappingTutorial.entities.EmployeeEntity;
import com.rohit.tutorial.dataMapping.dataMappingTutorial.repositories.DepartmentRepository;
import com.rohit.tutorial.dataMapping.dataMappingTutorial.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentEntity getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElse(null);
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(
                        employee -> {
                            department.setManager(employee);
                            return departmentRepository.save(department);
                        })
        ).orElse(null);

    }

    public DepartmentEntity assignedDepartmentOfManager(Long employeeId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        return employeeEntity.map(
                EmployeeEntity::getManagedDepartment).orElse(null);
    }
}
