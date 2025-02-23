package com.rohit.tutorial.dataMapping.dataMappingTutorial.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(name = "department_manager")
    private EmployeeEntity manager;

    @OneToMany(mappedBy = "workerDepartment")
    private Set<EmployeeEntity> workers;

    @ManyToMany(mappedBy = "freeLanceDepartment")
    private Set<EmployeeEntity> freelancers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }
}
