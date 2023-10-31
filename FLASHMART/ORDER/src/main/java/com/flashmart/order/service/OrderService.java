package com.flashmart.order.service;

import com.flashmart.order.model.orderModel;
import com.flashmart.order.repository.OrderRepository;
import com.flashmart.order.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public orderModel createOrder(orderModel order) {
        // Implement order creation logic here
        return orderRepository.save(order);
    }

    public List<orderModel> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<orderModel> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public orderModel updateOrder(Long id, orderModel updatedOrder) {
        Optional<orderModel> existingOrder = orderRepository.findById(id);

        if (existingOrder.isPresent()) {
            orderModel orderToUpdate = existingOrder.get();
            // Update the order details
            orderToUpdate.setCusid(updatedOrder.getCusid());
            orderToUpdate.setDeliver_address(updatedOrder.getDeliver_address());
            orderToUpdate.setPrice(updatedOrder.getPrice());
            orderToUpdate.setDeliver_cost(updatedOrder.getDeliver_cost());
            orderToUpdate.setOrder_status(updatedOrder.isOrder_status());
            orderToUpdate.setOrdered_date(updatedOrder.getOrdered_date());
            orderToUpdate.setOrdered_time(updatedOrder.getOrdered_time());
            orderToUpdate.setPaymentid(updatedOrder.getPaymentid());
            orderToUpdate.setDeliverid(updatedOrder.getDeliverid());
            return orderRepository.save(orderToUpdate);
        } else {
            // Handle the case where the order with the specified id does not exist
            return null;
        }
    }

    public void deleteOrder(Long id) {
        Optional<orderModel> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            orderRepository.deleteById(id);

        } else {
            // Handle the case where the order with the specified id does not exist
            throw new EntityNotFoundException(id );
            // You can use a custom exception type or any appropriate error handling mechanism
        }
    }

}
