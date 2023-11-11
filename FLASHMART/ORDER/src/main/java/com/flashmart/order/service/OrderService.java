package com.flashmart.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashmart.order.dto.*;
import com.flashmart.order.model.orderModel;
import com.flashmart.order.model.payment;
import com.flashmart.order.repository.OrderRepository;
import com.flashmart.order.exception.EntityNotFoundException;
import com.flashmart.order.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private  final MicroServicesConnectorService microServicesConnectorService;

    public OrderService(OrderRepository orderRepository, MicroServicesConnectorService microServicesConnectorService) {
        this.orderRepository = orderRepository;
        this.microServicesConnectorService = microServicesConnectorService;
    }


    public String orderArrival(OrderDTO orderDTO) {
        orderModel order = new orderModel();
        List<OrderedItem> orderedItem = new ArrayList<>();
        order.setCusid(orderDTO.getCustomerId());
        order.setDeliver_address(null);
        order.setPrice(orderDTO.getTotalPrice() - orderDTO.getDiscounts());
        order.setDeliver_cost(0);
        order.setOrder_status(false);
        order.setDeliverid(null);

        payment payment = new payment();
        payment.setPaymentAmount(orderDTO.getTotalPrice()-orderDTO.getDiscounts());
        payment.setPaymentType(null);
        payment.setDateAndTime(null);

        order.setPayment(payment);

        for (CartItemDTO cartItemDTO : orderDTO.getItems()) {
            OrderedItem newOrderedItem = new OrderedItem();
            newOrderedItem.setProductId(cartItemDTO.getItemCode());
            newOrderedItem.setQuantity(cartItemDTO.getQuantity());
            orderedItem.add(newOrderedItem);
        }

        order.setOrderedItems(orderedItem);
        createOrder(order);

        return "Order Received!";
    }


    public void decreaseInventoryProducts(Long orderId) {
        orderModel order = getOrderById(orderId).orElse(null);
        List<OrderedItem> orderedItems = order.getOrderedItems();

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<OrderedItemDTO> orderedItemDTOList = new ArrayList<>();
            for (OrderedItem orderedItem : orderedItems) {
                OrderedItemDTO orderedItemDTO = new OrderedItemDTO();
                orderedItemDTO.setItemCode(orderedItem.getProductId());
                orderedItemDTO.setNoOfProducts(orderedItem.getQuantity());
                orderedItemDTOList.add(orderedItemDTO);
            }

            String jsonInputString = objectMapper.writeValueAsString(orderedItemDTOList);

            microServicesConnectorService.postAPI("http://localhost:8082/api/inventory/decrease", jsonInputString, Void.class);

        } catch (JsonProcessingException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void createDeliveryEntry(Long orderId) {
        DeliveryEntryRequest request = new DeliveryEntryRequest();
        request.setOrderId(String.valueOf(orderId));
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String jsonInputString = objectMapper.writeValueAsString(request);

            microServicesConnectorService.postAPI("http://localhost:8084/api/delivery", jsonInputString, Void.class);

        } catch (JsonProcessingException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public orderModel createOrder(orderModel order) {
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

            orderToUpdate.setCusid(updatedOrder.getCusid());
            orderToUpdate.setDeliver_address(updatedOrder.getDeliver_address());
            orderToUpdate.setPrice(updatedOrder.getPrice());
            orderToUpdate.setDeliver_cost(updatedOrder.getDeliver_cost());
            orderToUpdate.setOrder_status(updatedOrder.isOrder_status());
            orderToUpdate.setOrdered_date(updatedOrder.getOrdered_date());
            orderToUpdate.setOrdered_time(updatedOrder.getOrdered_time());
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
        try {
            ProductDTO productDTO = microServicesConnectorService.fetchAPI("http://localhost:8082/api/inventory/productById", orderedItemDTO.getItemCode(), ProductDTO.class);

            if (order != null && productDTO != null) {
                OrderedItem orderedItem = new OrderedItem();
                orderedItem.setOrder(order);
                orderedItem.setProductId(productDTO.getItemCode());
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
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<orderModel> getOrdersByCustomerId(String customerId) {
        // Implement logic to retrieve orders by customer ID
        return orderRepository.findByCustomerId(customerId);
    }


}
