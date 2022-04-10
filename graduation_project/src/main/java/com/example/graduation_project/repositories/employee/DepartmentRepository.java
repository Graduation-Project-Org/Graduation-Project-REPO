package com.example.graduation_project.repositories.employee;

import com.example.graduation_project.entities.employee.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository< DepartmentEntity, Long > {
}
