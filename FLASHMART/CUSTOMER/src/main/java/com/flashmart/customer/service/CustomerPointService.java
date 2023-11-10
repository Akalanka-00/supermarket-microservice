package com.flashmart.customer.service;

import com.flashmart.customer.dto.CartDTO;
import com.flashmart.customer.dto.PointDTO;
import com.flashmart.customer.model.Cart;
import com.flashmart.customer.model.PointData;
import com.flashmart.customer.repository.CustomerCartRepository;
import com.flashmart.customer.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerPointService {

    @Autowired
    private final CustomerCartService customerCartService;

    @Autowired
    private final PointRepository pointRepository;

    @Autowired
    private final CustomerCartRepository customerCartRepository;

    public CustomerPointService(CustomerCartService customerCartService, PointRepository pointRepository, CustomerCartRepository customerCartRepository) {
        this.customerCartService = customerCartService;
        this.pointRepository = pointRepository;
        this.customerCartRepository = customerCartRepository;
    }

    public PointDTO getPointByCustomerId(Long customerId) {
        CartDTO cartDTO = customerCartService.getCartByCustomerId(customerId).getBody();
        double customerPoints = cartDTO.getPoint();

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
            Cart cart = customerCartRepository.findById(cartDTO.getCartId()).orElse(null);
            cart.setPoint(customerPoints);
            customerCartRepository.save(cart);
        }

        return pointDTO;
    }
}


