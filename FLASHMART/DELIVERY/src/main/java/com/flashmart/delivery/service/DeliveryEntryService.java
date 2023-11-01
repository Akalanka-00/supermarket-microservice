package com.flashmart.delivery.service;

import com.flashmart.delivery.Consts.KAFKA_HEADERS;
import com.flashmart.delivery.Consts.USER_TYPES;
import com.flashmart.delivery.dto.DeliveryEntryRequest;
import com.flashmart.delivery.event.NotificationBuilder;
import com.flashmart.delivery.model.DeliveryEntryModel;
import com.flashmart.delivery.repository.DeliveryEntryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryEntryService {

    private final DeliveryEntryRepository deliveryEntryRepository;
//    private final KafkaTemplate<String, NotificationBuilder> kafkaTemplate;

    public void createDeliveryEntry( DeliveryEntryRequest request){

        DeliveryEntryModel model = DeliveryEntryModel.builder()
                .orderId(request.getOrderId())
                .pickedUpTime(new Date())
                .deliveredTime(new Date())
                .build();

        deliveryEntryRepository.save(model);
//        kafkaTemplate.send(KAFKA_HEADERS.NOTIFICATION, NotificationBuilder.create().BroadcastNotification("delivery broadcast","This is a broadcast message from delivery","/", List.of(USER_TYPES.DELIVERY_PERSON)));
    }

    



}
