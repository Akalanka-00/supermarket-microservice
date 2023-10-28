package com.flashmart.delivery.controller;

import com.flashmart.delivery.dto.DeliverUserRequest;
import com.flashmart.delivery.dto.DeliverUserResponse;
import com.flashmart.delivery.dto.DeliveryEntryRequest;
import com.flashmart.delivery.dto.DeliveryEntryResponse;
import com.flashmart.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor

public class DeliveryController {

    private  final DeliveryService deliveryService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void createDeliveryEntry(@RequestBody DeliveryEntryRequest deliveryEntry){
        deliveryService.createDeliveryEntry(deliveryEntry);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<DeliveryEntryResponse> getAllDeliveryEntries(){
        return deliveryService.getAllDeliveryEntries();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public DeliveryEntryResponse getDeliveryEntryById(@PathVariable String id){
        return deliveryService.getDeliveryById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void updateDeliveryDetails(@PathVariable String id, DeliveryEntryRequest deliveryEntryRequest){
        deliveryService.updateDeliveryById(id, deliveryEntryRequest);
    }
}
