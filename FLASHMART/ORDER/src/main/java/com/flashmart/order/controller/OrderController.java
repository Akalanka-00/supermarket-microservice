package com.flashmart.order.controller;

import com.flashmart.order.model.orderModel;
import com.flashmart.order.dto.OrderObject;
import com.flashmart.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public orderModel createOrder(@RequestBody orderModel order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/getAllOrders")
    public List<orderModel> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/getOrderById/{id}")
    public orderModel getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id).orElse(null);
    }

    @PutMapping("/updateOrder/{id}")
    public orderModel updateOrder(@PathVariable Long id, @RequestBody orderModel updatedOrder) {
        return orderService.updateOrder(id, updatedOrder);
    }


    @DeleteMapping("/deleteOrder/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
