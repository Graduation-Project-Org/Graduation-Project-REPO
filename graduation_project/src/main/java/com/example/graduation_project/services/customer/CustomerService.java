package com.example.graduation_project.services.customer;

import com.example.graduation_project.entities.customer.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {
    Page< CustomerEntity > findAll (Pageable pageable);
    Optional<CustomerEntity> findById(Long customerId);
    void save(CustomerEntity customerEntity);
    void delete(Long customerId);
}
