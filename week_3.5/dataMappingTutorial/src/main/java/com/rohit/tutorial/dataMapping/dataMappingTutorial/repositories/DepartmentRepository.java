package com.rohit.tutorial.dataMapping.dataMappingTutorial.repositories;

import com.rohit.tutorial.dataMapping.dataMappingTutorial.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
