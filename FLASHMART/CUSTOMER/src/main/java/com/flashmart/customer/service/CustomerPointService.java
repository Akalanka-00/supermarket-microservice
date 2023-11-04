package com.flashmart.customer.service;

import com.flashmart.customer.repository.CustomerPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerPointService {

    @Autowired
    private final CustomerPointRepository customerPointRepository;

    public CustomerPointService(CustomerPointRepository customerPointRepository) {
        this.customerPointRepository = customerPointRepository;
    }
}
