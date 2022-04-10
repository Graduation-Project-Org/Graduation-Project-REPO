package com.example.graduation_project.serviceimpl.customer;

import com.example.graduation_project.entities.customer.CustomerEntity;
import com.example.graduation_project.repositories.customer.CustomerRepository;
import com.example.graduation_project.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Page< CustomerEntity > findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Optional< CustomerEntity > findById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public void save(CustomerEntity customerEntity) {
        customerRepository.save(customerEntity);
    }

    @Override
    public void delete(Long customerId) {
        customerRepository.deleteById(customerId);

    }

}
