package com.flashmart.delivery.controller;

import com.flashmart.delivery.dto.DeliveryEntryRequest;
import com.flashmart.delivery.service.DeliveryEntryService;
import com.flashmart.delivery.service.DeliveryPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor

public class DeliveryEntryController {

    private  final DeliveryEntryService deliveryEntryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void createDeliveryEntry(@RequestBody DeliveryEntryRequest request){
        deliveryEntryService.createDeliveryEntry(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/setAsPicked/{id}")
    public String MarkAsPicked(@PathVariable String id){
        return deliveryEntryService.markAsPicked(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/setAsDelivered/{id}")
    public String MarkAsDelivered(@PathVariable String id){
        return deliveryEntryService.markAsDelivered(id);
    }
}
