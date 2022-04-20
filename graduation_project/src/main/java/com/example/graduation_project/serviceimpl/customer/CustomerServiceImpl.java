package com.example.graduation_project.serviceimpl.customer;

import com.example.graduation_project.dto.CustomerDto;
import com.example.graduation_project.entities.customer.CustomerEntity;
import com.example.graduation_project.repositories.customer.CustomerRepository;
import com.example.graduation_project.services.customer.CustomerService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page< CustomerEntity > findAllByNameAndPhoneAndAddress(String name, String phone, String address, Pageable pageable) {
        return customerRepository.FindAllCustomerByNamePhoneAddress(
                name, phone, address, pageable
        );
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

    @Override
    public CustomerEntity converseCustomerToDTO(CustomerDto customerDto, Long id) {
        return null;
    }

//    @Override
//    public CustomerEntity converseCustomerToDTO(CustomerDto customerDto, Long id) {
//        Optional<CustomerEntity> customerEntity = customerService.findById(id);
//        BeanUtils.copyProperties(customerDto, customerEntity.get());
//        return customerEntity.get();
//    }
}
