package com.flashmart.customer.service;

import com.flashmart.customer.repository.CustomerFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerFeedbackService {

    @Autowired
    private final CustomerFeedbackRepository customerFeedbackRepository;

    public CustomerFeedbackService(CustomerFeedbackRepository customerFeedbackRepository) {
        this.customerFeedbackRepository = customerFeedbackRepository;
    }
}
