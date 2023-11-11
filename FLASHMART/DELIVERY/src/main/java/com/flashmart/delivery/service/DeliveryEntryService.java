package com.flashmart.delivery.service;

import com.flashmart.delivery.Consts.KAFKA_HEADERS;
import com.flashmart.delivery.Consts.USER_TYPES;
import com.flashmart.delivery.dto.DeliveryEntryRequest;
import com.flashmart.delivery.dto.DeliveryEntryResponse;
import com.flashmart.delivery.event.EmailBuilder;
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
                .pickedUpTime(null)
                .deliveredTime(null)
                .status(10120).build();

        deliveryEntryRepository.save(model);

//         kafkaTemplate.send(KAFKA_HEADERS.NOTIFICATION, NotificationBuilder.create().BroadcastNotification("delivery broadcast","This is a broadcast message from delivery","/", List.of(USER_TYPES.DELIVERY_PERSON)));
//         kafkaTemplate.send(KAFKA_HEADERS.NOTIFICATION, NotificationBuilder.create().UserNotification("User broadcast","This is a broadcast message from delivery","/", List.of("D0001","D0002")));
//         kafkaTemplate.send(KAFKA_HEADERS.NOTIFICATION, NotificationBuilder.create().SendEmail("delivery broadcast",new EmailBuilder("shenalakalanka513@gmail.com","Test Email","Hello user! this is a test mail")));
    }

    public String markAsPicked(String id){
        DeliveryEntryModel model = deliveryEntryRepository.findById(id).orElse(null);
        if(model!=null){
            model.setPickedUpTime(new Date());
            deliveryEntryRepository.save(model);
            return ("Package is picked up by "+id);
        }
        return ("Error occurred at pickedTime");
    }

    public String markAsDelivered(String id){
        DeliveryEntryModel model = deliveryEntryRepository.findById(id).orElse(null);
        if(model!=null){
            model.setDeliveredTime(new Date());
            deliveryEntryRepository.save(model);
            return ("Package is Delivered up by "+id);
        }
        return ("Error occurred");
    }
}
