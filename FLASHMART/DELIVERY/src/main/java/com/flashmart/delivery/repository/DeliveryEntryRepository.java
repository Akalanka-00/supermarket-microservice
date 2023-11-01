package com.flashmart.delivery.repository;

import com.flashmart.delivery.model.DeliveryEntryModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryEntryRepository extends MongoRepository<DeliveryEntryModel, String> {
}
