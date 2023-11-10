package com.flashmart.delivery.repository;

import com.flashmart.delivery.model.VehicleModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<VehicleModel, String> {
}
