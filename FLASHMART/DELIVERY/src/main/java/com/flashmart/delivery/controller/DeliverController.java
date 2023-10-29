package com.flashmart.delivery.controller;


import com.flashmart.delivery.dto.DeliverUserRequest;
import com.flashmart.delivery.dto.DeliverUserResponse;
import com.flashmart.delivery.service.DeliverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliver/user")
@RequiredArgsConstructor
public class DeliverController {

    private final DeliverService deliverService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void createNewDeliveryUser(DeliverUserRequest deliverUser){
        deliverService.createDeliverUser(deliverUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<DeliverUserResponse> getDeliveryUsers(){
        return deliverService.getAllDeliverUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public DeliverUserResponse getDeliveryUser(@PathVariable String id){
        return deliverService.getDeliverUserById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void updateDeliveryUser(@PathVariable String id, DeliverUserRequest deliverUser){
         deliverService.updateDeliverUserById(id, deliverUser);
    }
}
