package com.flashmart.delivery.controller;


import com.flashmart.delivery.dto.DeliverUserRequest;
import com.flashmart.delivery.dto.DeliverUserResponse;
import com.flashmart.delivery.service.DeliverService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery/user")
@RequiredArgsConstructor
public class DeliverController {

    private final DeliverService deliverService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    @CircuitBreaker(name = "deliver", fallbackMethod = "fallbackMethod")
    public void createNewDeliveryUser(DeliverUserRequest deliverUser){
        deliverService.createDeliverUser(deliverUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    @CircuitBreaker(name = "deliver", fallbackMethod = "fallbackMethod")
    public List<DeliverUserResponse> getDeliveryUsers(){
        return deliverService.getAllDeliverUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @CircuitBreaker(name = "deliver", fallbackMethod = "fallbackMethod")
    public DeliverUserResponse getDeliveryUser(@PathVariable String id){
        return deliverService.getDeliverUserById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    @CircuitBreaker(name = "deliver", fallbackMethod = "fallbackMethod")
    public void updateDeliveryUser(@PathVariable String id, DeliverUserRequest deliverUser){
         deliverService.updateDeliverUserById(id, deliverUser);
    }

    public String fallbackMethod(){
        return "Oops! something is wrong. Please try again!";
    }
}
