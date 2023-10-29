package com.flashmart.delivery.repository;

import com.flashmart.delivery.model.DeliverModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliverRepository extends MongoRepository<DeliverModel, String> {

}
