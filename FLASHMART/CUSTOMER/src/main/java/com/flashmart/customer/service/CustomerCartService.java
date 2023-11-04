package com.flashmart.customer.service;

import com.flashmart.customer.dto.CartDTO;
import com.flashmart.customer.model.Cart;
import com.flashmart.customer.model.Customer;
import com.flashmart.customer.model.Item;
import com.flashmart.customer.repository.CustomerCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCartService {

    @Autowired
    private final CustomerCartRepository customerCartRepositoryRepository;

    public CustomerCartService(CustomerCartRepository customerCartRepositoryRepository) {
        this.customerCartRepositoryRepository = customerCartRepositoryRepository;
    }

}
