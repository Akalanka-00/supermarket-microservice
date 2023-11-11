package com.flashmart.order.controller;

import com.flashmart.order.dto.OrderDTO;
import com.flashmart.order.dto.OrderedItem;
import com.flashmart.order.dto.OrderedItemDTO;
import com.flashmart.order.model.orderModel;
import com.flashmart.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orderArrival")
    public ResponseEntity<Map<String, String>> orderArrival(@RequestBody OrderDTO orderDTO) {
        Map<String, String> response = new HashMap<>();
        response.put("message", orderService.orderArrival(orderDTO));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createOrder")
    public orderModel createOrder(@RequestBody orderModel order) {
        orderService.createOrder(order);
        return order;
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


    @GetMapping("/getOrderedItems/{orderId}")
    public List<OrderedItem> getOrderedItems(@PathVariable Long orderId) {
        // Call the service layer to retrieve ordered items by order ID
        return orderService.getOrderedItems(orderId);
    }

    @PostMapping("/addOrderedItem/{orderId}")
    public orderModel addOrderedItemToOrder(@PathVariable Long orderId, @RequestBody OrderedItemDTO orderedItemDTO) {
        return orderService.addOrderedItemToOrder(orderId, orderedItemDTO);
    }

    @GetMapping("/getOrdersByCustomerId/{customerId}")
    public List<orderModel> getOrdersByCustomerId(@PathVariable String customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }


}
