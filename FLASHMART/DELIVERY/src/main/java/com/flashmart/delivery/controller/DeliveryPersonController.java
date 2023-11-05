package com.flashmart.delivery.controller;

import com.flashmart.delivery.dto.DeliveryPersonRequest;
import com.flashmart.delivery.service.DeliveryPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery/user")
@RequiredArgsConstructor
public class DeliveryPersonController {

    private final DeliveryPersonService deliveryPersonService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void newDeliveryPerson(@RequestBody DeliveryPersonRequest request){
        deliveryPersonService.newDeliveryPerson(request);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("status/{id}")
    public String changeAvailability(@PathVariable String id){
       return deliveryPersonService.changeAvailability(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("addVehicle/{id}")
    public String addVehicleToDeliveryPerson(@PathVariable String id, @RequestBody DeliveryPersonRequest request){
        return deliveryPersonService.addVehicleToDeliveryPerson(id, request);
    }
}
