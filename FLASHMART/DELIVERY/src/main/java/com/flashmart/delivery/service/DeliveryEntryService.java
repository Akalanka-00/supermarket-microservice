package com.flashmart.delivery.service;

import com.flashmart.delivery.dto.DeliveryEntryRequest;
import com.flashmart.delivery.dto.DeliveryEntryResponse;
import com.flashmart.delivery.model.DeliveryEntryModel;
import com.flashmart.delivery.repository.DeliveryEntryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryEntryService {

    private final DeliveryEntryRepository deliveryEntryRepository;

    public void createDeliveryEntry(DeliveryEntryRequest request){

        DeliveryEntryModel model = DeliveryEntryModel.builder()
                .orderId("O0001")
                .pickedUpTime(new Date())
                .deliveredTime(new Date())
                .build();

        deliveryEntryRepository.save(model);
    }



}
