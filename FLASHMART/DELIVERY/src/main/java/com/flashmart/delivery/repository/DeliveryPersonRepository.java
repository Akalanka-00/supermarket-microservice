package com.flashmart.delivery.repository;

import com.flashmart.delivery.model.DeliveryPersonModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryPersonRepository extends MongoRepository<DeliveryPersonModel, String> {

}
