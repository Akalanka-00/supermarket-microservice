package com.flashmart.customer.service;

import com.flashmart.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerPointService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerPointService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
