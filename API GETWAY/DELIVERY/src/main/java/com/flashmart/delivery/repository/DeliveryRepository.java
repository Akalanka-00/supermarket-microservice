package com.flashmart.delivery.repository;

import com.flashmart.delivery.model.DeliveryModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryRepository extends MongoRepository<DeliveryModel, String> {
}
