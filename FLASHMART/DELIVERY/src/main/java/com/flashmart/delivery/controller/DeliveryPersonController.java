package com.flashmart.delivery.controller;

import com.flashmart.delivery.dto.DeliveryPersonAllDetailsResponse;
import com.flashmart.delivery.dto.DeliveryPersonRequest;
import com.flashmart.delivery.dto.DeliveryPersonResponse;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/newUser/{id}")
    public String newDeliveryPerson(@PathVariable int id){
        return deliveryPersonService.newDeliveryPerson(id);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("status/{id}")
    public String changeAvailability(@PathVariable String id){
       return deliveryPersonService.changeAvailability(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("updateFeedback/{id}")
    public String UpdateFeedback(@PathVariable String id){
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

    @GetMapping("allDetails/{id}")
    public ResponseEntity<DeliveryPersonAllDetailsResponse> getDeliveryPersonDetailsById(@PathVariable String id){
        return deliveryPersonService.getDeliveryPersonDetailsById(id);
    }

    @GetMapping("allDetails")
    public List<DeliveryPersonAllDetailsResponse> getAllDeliveryPersonsDetails(){
        return deliveryPersonService.getAllDeliveryPersonsDetails();
    }

    @GetMapping("getAvailable")
    public ResponseEntity<DeliveryPersonAllDetailsResponse> getAvailableDeliveryPerson(){
        return deliveryPersonService.getAvailableDeliveryPerson();
    }
}
