package com.flashmart.delivery.service;


import com.flashmart.delivery.Consts.DELIVER_AVAILABILITY;
import com.flashmart.delivery.dto.DeliveryPersonRequest;
import com.flashmart.delivery.model.DeliveryEntryModel;
import com.flashmart.delivery.model.DeliveryPersonModel;
import com.flashmart.delivery.repository.DeliveryPersonRepository;
import com.flashmart.delivery.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryPersonService {

    private final DeliveryPersonRepository deliveryPersonRepository;
    private final VehicleRepository vehicleRepository;
     public void newDeliveryPerson(DeliveryPersonRequest request){
         if (vehicleRepository.existsById(request.getVehicleID())) {
             DeliveryPersonModel model = DeliveryPersonModel.builder()
                     .availability(DELIVER_AVAILABILITY.UNAVAILABLE)
                     .vehicleID(request.getVehicleID())
                     .build();

             deliveryPersonRepository.save(model);
         } else {
             System.out.println("Vehicle not found");
         }
     }
}
