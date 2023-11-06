package com.flashmart.delivery.controller;

import com.flashmart.delivery.dto.DeliveryPersonRequest;
import com.flashmart.delivery.model.DeliveryPersonModel;
import com.flashmart.delivery.service.DeliveryPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("all")
    public List<DeliveryPersonModel> getAllDeliveryPerson(){
        return deliveryPersonService.getAllDeliveryPerson();
    }

    @GetMapping("{id}")
    public ResponseEntity<DeliveryPersonModel> getDeliveryPersonById(@PathVariable String id) {
        return deliveryPersonService.getDeliveryPersonById(id);
    }
}
