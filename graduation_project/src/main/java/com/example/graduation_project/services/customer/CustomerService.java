package com.example.graduation_project.services.customer;

import com.example.graduation_project.dto.CustomerDto;
import com.example.graduation_project.entities.customer.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Page< CustomerEntity > findAllByNameAndPhoneAndAddress(
            String name,
            String phone,
            String address,
            Pageable pageable);

    Optional< CustomerEntity > findById(Long customerId);

    void save(CustomerEntity customerEntity);

    void delete(Long customerId);
    CustomerEntity converseCustomerToDTO(CustomerDto customerDto,  Long id);
}
