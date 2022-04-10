package com.example.graduation_project.repositories.customer;

import com.example.graduation_project.entities.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository< CustomerEntity, Long > {
}
