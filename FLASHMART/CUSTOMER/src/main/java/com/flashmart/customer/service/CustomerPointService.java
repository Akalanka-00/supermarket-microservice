package com.flashmart.customer.service;

import com.flashmart.customer.dto.PointDTO;
import com.flashmart.customer.exception.ResourceNotFoundException;
import com.flashmart.customer.model.Customer;
import com.flashmart.customer.model.PointData;
import com.flashmart.customer.repository.CustomerCartRepository;
import com.flashmart.customer.repository.CustomerRepository;
import com.flashmart.customer.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerPointService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final PointRepository pointRepository;

    @Autowired
    private final CustomerCartRepository customerCartRepository;

    public CustomerPointService(CustomerRepository customerRepository, PointRepository pointRepository, CustomerCartRepository customerCartRepository) {
        this.customerRepository = customerRepository;
        this.pointRepository = pointRepository;
        this.customerCartRepository = customerCartRepository;
    }

    public PointDTO getPointByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));

        double customerPoints = customer.getPoints();

        List<PointData> points = pointRepository.findAll();
        int applicableDiscount = 0;
        for (PointData point : points) {
            if (customerPoints >= point.getLimitValue()) {
                applicableDiscount = point.getDiscount();
            }
        }

        double totalPrice = customerCartRepository.findTotalPriceByCustomerId(customerId);

        double discount = totalPrice * applicableDiscount / 100;

        PointDTO pointDTO = new PointDTO();
        pointDTO.setDiscount(discount);
        pointDTO.setPoints(customerPoints);
        pointDTO.setCustomerId(customerId);


        if (totalPrice >= 1000) {
            customerPoints += 5;
            customer.setPoints(customerPoints);
            customerRepository.save(customer);
        }

        return pointDTO;
    }
}


