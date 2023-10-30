package com.flashmart.delivery.service;

import com.flashmart.delivery.Consts.KAFKA_HEADERS;
import com.flashmart.delivery.dto.DeliverUserResponse;
import com.flashmart.delivery.dto.DeliveryEntryRequest;
import com.flashmart.delivery.dto.DeliveryEntryResponse;
import com.flashmart.delivery.event.NotificationBuilder;
import com.flashmart.delivery.model.DeliveryModel;
import com.flashmart.delivery.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final KafkaTemplate<String, NotificationBuilder> kafkaTemplate;
    public void createDeliveryEntry(DeliveryEntryRequest deliveryEntry){
        DeliveryModel model = DeliveryModel.builder()

                .customerId(deliveryEntry.getCustomerId())
                .deliverId(deliveryEntry.getDeliverId())
                .orderId(deliveryEntry.getOrderId())
                .status(deliveryEntry.getStatus())

                .build();

        deliveryRepository.save(model);
        kafkaTemplate.send(KAFKA_HEADERS.NOTIFICATION, new NotificationBuilder("New delivery added","This notification is send for update in the delivery system.","/"));
        log.info("Delivery Entity has been added", model.getId());
    }

    public List<DeliveryEntryResponse> getAllDeliveryEntries() {

        List<DeliveryModel> model = deliveryRepository.findAll();

        return model.stream().map(this::mapToDeliveryResponse).toList();

    }

    private DeliveryEntryResponse mapToDeliveryResponse(DeliveryModel deliveryModel) {
        return DeliveryEntryResponse.builder()
                .id(deliveryModel.getId())
                .customerId(deliveryModel.getCustomerId())
                .deliverId(deliveryModel.getDeliverId())
                .orderId(deliveryModel.getOrderId())
                .status(deliveryModel.getStatus())

                .build();

    }

    public DeliveryEntryResponse getDeliveryById(String id) {
        DeliveryModel model = deliveryRepository.findById(id).orElse(null);
        if(model!=null){
            return DeliveryEntryResponse.builder()
                    .id(model.getId())
                    .orderId(model.getOrderId())
                    .customerId(model.getCustomerId())
                    .deliverId(model.getDeliverId())
                    .status(model.getStatus())
                    .build();
        }

        return null;
    }

    public void updateDeliveryById(String id, DeliveryEntryRequest deliveryEntryRequest) {
        DeliveryModel existModel = deliveryRepository.findById(id).orElse(null);
        if(existModel!=null){
            if (deliveryEntryRequest.getCustomerId()!=null)
                existModel.setCustomerId(deliveryEntryRequest.getCustomerId());

            if (deliveryEntryRequest.getDeliverId()!=null)
                existModel.setDeliverId(deliveryEntryRequest.getDeliverId());

            if (deliveryEntryRequest.getOrderId()!=null)
                existModel.setOrderId(deliveryEntryRequest.getOrderId());

        }
    }
}
