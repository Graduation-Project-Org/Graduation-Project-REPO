package com.example.graduation_project.repositories.employee;

import com.example.graduation_project.entities.employee.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository< EmployeeEntity, Long > {
}
