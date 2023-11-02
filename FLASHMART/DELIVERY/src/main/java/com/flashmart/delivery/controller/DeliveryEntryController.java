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

    private  final DeliveryEntryService deliveryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void createDeliveryEntry(DeliveryEntryRequest request){
        deliveryService.createDeliveryEntry(request);
    }

}
