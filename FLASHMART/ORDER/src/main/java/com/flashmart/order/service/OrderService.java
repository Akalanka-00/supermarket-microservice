package com.flashmart.order.service;

import com.flashmart.order.dto.OrderDTO;
import com.flashmart.order.dto.OrderedItem;
import com.flashmart.order.dto.OrderedItemDTO;
import com.flashmart.order.model.orderModel;
import com.flashmart.order.model.Product; // Import the Product entity
import com.flashmart.order.repository.OrderRepository;
import com.flashmart.order.repository.ProductRepository;
import com.flashmart.order.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,ProductRepository productRepository) {

        this.orderRepository = orderRepository;
        this.productRepository=productRepository;
    }

    public String orderArrival(OrderDTO orderDTO) {
        return "Order Received!";
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


    public List<OrderedItem> getOrderedItems(Long orderId) {
        // Retrieve ordered items by order ID, you can implement this logic
        // based on how you have structured your data and relationships.
        // For example, you can fetch ordered items associated with the given order.
        // Here, I assume there's a method getOrderById in the repository.
        orderModel order = orderRepository.findById(orderId).orElse(null);

        if (order != null) {
            return order.getOrderedItems();
        } else {
            // Handle the case where the order with the specified ID does not exist
            throw new EntityNotFoundException(orderId);
        }
    }

    public orderModel addOrderedItemToOrder(Long orderId, OrderedItemDTO orderedItemDTO) {
        orderModel order = orderRepository.findById(orderId).orElse(null);
        Product product = productRepository.findById(orderedItemDTO.getProductId()).orElse(null);

        if (order != null && product != null) {
            OrderedItem orderedItem = new OrderedItem();
            orderedItem.setOrder(order);
            orderedItem.setProduct(product);
            orderedItem.setQuantity(orderedItem.getQuantity());

            List<OrderedItem> orderedItems = order.getOrderedItems();
            orderedItems.add(orderedItem);
            order.setOrderedItems(orderedItems);
            // Update total price or any other relevant order information
            return orderRepository.save(order);
        } else {
            // Handle the case where the order or product with the specified IDs do not exist
            throw new EntityNotFoundException(orderId);
        }
    }

    public List<orderModel> getOrdersByCustomerId(String customerId) {
        // Implement logic to retrieve orders by customer ID
        return orderRepository.findByCustomerId(customerId);
    }


}
