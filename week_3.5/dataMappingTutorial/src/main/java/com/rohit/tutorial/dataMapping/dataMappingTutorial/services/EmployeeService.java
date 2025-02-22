package com.rohit.tutorial.dataMapping.dataMappingTutorial.services;

import com.rohit.tutorial.dataMapping.dataMappingTutorial.entities.EmployeeEntity;
import com.rohit.tutorial.dataMapping.dataMappingTutorial.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private EmployeeEntity createNewEmployee(EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }

    private EmployeeEntity getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }
}
