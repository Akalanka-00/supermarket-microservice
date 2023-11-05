package com.flashmart.delivery.service;


import com.flashmart.delivery.Consts.DELIVER_AVAILABILITY;
import com.flashmart.delivery.dto.DeliveryPersonRequest;
import com.flashmart.delivery.model.DeliveryPersonModel;
import com.flashmart.delivery.model.VehicleModel;
import com.flashmart.delivery.repository.DeliveryPersonRepository;
import com.flashmart.delivery.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryPersonService {

    private final DeliveryPersonRepository deliveryPersonRepository;
    private final VehicleRepository vehicleRepository;
     public void newDeliveryPerson(DeliveryPersonRequest request){
         DeliveryPersonModel model = DeliveryPersonModel.builder()
                     .availability(DELIVER_AVAILABILITY.UNAVAILABLE)
                     .vehicleID(request.getVehicleID())
                     .build();

         deliveryPersonRepository.save(model);
     }

     public String changeAvailability(String id){
         DeliveryPersonModel model = deliveryPersonRepository.findById(id).orElse(null);
         if(model!=null){
             if (model.getAvailability()==DELIVER_AVAILABILITY.UNAVAILABLE){
                 if (model.getVehicleID()!=null){
                     model.setAvailability(DELIVER_AVAILABILITY.AVAILABLE);
                     deliveryPersonRepository.save(model);
                     return "Person is now available";
                 }else {
                     return "Vehicle not found";
                 }
             }
             if (model.getAvailability()==DELIVER_AVAILABILITY.AVAILABLE){
                 model.setAvailability(DELIVER_AVAILABILITY.UNAVAILABLE);
                 deliveryPersonRepository.save(model);
                 return "Person is not available now";
             }
         }else return "User "+ id+" not found";
         return "Error occurred";
     }

     public String addVehicleToDeliveryPerson(String id, DeliveryPersonRequest request){
         DeliveryPersonModel model = deliveryPersonRepository.findById(id).orElse(null);
         if(model!=null){
             if (request.getVehicleID() != null){
                 if (vehicleRepository.existsById(request.getVehicleID())){
                     model.setVehicleID(request.getVehicleID());
                     deliveryPersonRepository.save(model);
                     return "Vehicle"+request.getVehicleID()+" is added to the DeliveryPerson" + id;
                 }
                 return "Vehicle is not Registered";
             }
             return "Vehicle is not found";
         }
         return "Error occurred";
     }
}
