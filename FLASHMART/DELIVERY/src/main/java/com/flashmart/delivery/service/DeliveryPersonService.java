package com.flashmart.delivery.service;


import com.flashmart.delivery.Consts.DELIVER_AVAILABILITY;
import com.flashmart.delivery.dto.DeliveryEntryResponse;
import com.flashmart.delivery.dto.DeliveryPersonAllDetailsResponse;
import com.flashmart.delivery.dto.DeliveryPersonRequest;
import com.flashmart.delivery.dto.DeliveryPersonResponse;
import com.flashmart.delivery.exception.ResourceNotFoundException;
import com.flashmart.delivery.model.DeliveryPersonModel;
import com.flashmart.delivery.model.VehicleModel;
import com.flashmart.delivery.repository.DeliveryPersonRepository;
import com.flashmart.delivery.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                 .latitude(0)
                 .longitude(0)
                 .lastUpdatedTime(null)
                 .total_rating(0).no_of_rated_users(0).build();

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

     public List<DeliveryPersonModel> getAllDeliveryPerson(){
        return deliveryPersonRepository.findAll();
     }

     public ResponseEntity<DeliveryPersonModel> getDeliveryPersonById(String id){
        DeliveryPersonModel model = deliveryPersonRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Delivery Person does not exist with id: " + id));
        return ResponseEntity.ok(model);
     }

    public ResponseEntity<DeliveryPersonAllDetailsResponse> getDeliveryPersonDetailsById(String id) {
        DeliveryPersonModel deliveryPersonModel = deliveryPersonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Person does not exist with id: " + id));

        VehicleModel vehicleModel = vehicleRepository.findById(deliveryPersonModel.getVehicleID())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle does not exist with id: " + deliveryPersonModel.getVehicleID()));

        DeliveryPersonAllDetailsResponse response = DeliveryPersonAllDetailsResponse.builder()
                .id(deliveryPersonModel.getId())
                .availability(deliveryPersonModel.getAvailability())
                .rating((double) deliveryPersonModel.getTotal_rating() /deliveryPersonModel.getNo_of_rated_users())
                .latitude(deliveryPersonModel.getLatitude())
                .longitude(deliveryPersonModel.getLongitude())
                .lastUpdatedTime(deliveryPersonModel.getLastUpdatedTime())
                .color(vehicleModel.getColor())
                .vehicleNo(vehicleModel.getVehicleNo())
                .vehicleType(vehicleModel.getVehicleType())
                .build();

        return ResponseEntity.ok(response);
    }

    public List<DeliveryPersonAllDetailsResponse> getAllDeliveryPersonsDetails() {
        List<DeliveryPersonModel> deliveryPersons = deliveryPersonRepository.findAll();
        return deliveryPersons.stream()
                .map(deliveryPersonModel -> {
                    VehicleModel vehicleModel = null;

                    if (deliveryPersonModel.getVehicleID() != null) {
                        vehicleModel = vehicleRepository.findById(deliveryPersonModel.getVehicleID())
                                .orElseThrow(() -> new ResourceNotFoundException("Vehicle does not exist with id: " + deliveryPersonModel.getVehicleID()));
                    }

                    return DeliveryPersonAllDetailsResponse.builder()
                            .id(deliveryPersonModel.getId())
                            .availability(deliveryPersonModel.getAvailability())
                            .rating((double) deliveryPersonModel.getTotal_rating() /deliveryPersonModel.getNo_of_rated_users())
                            .latitude(deliveryPersonModel.getLatitude())
                            .longitude(deliveryPersonModel.getLongitude())
                            .lastUpdatedTime(deliveryPersonModel.getLastUpdatedTime())
                            .color(vehicleModel != null ? vehicleModel.getColor() : null)
                            .vehicleNo(vehicleModel != null ? vehicleModel.getVehicleNo() : null)
                            .vehicleType(vehicleModel != null ? vehicleModel.getVehicleType() : null)
                            .build();
                })
                .collect(Collectors.toList());
    }

    public ResponseEntity<DeliveryPersonAllDetailsResponse> getAvailableDeliveryPerson(){
        List<DeliveryPersonModel> deliveryPersons = deliveryPersonRepository.findAll();

        for (DeliveryPersonModel model : deliveryPersons) {
            if (model.getAvailability() == DELIVER_AVAILABILITY.AVAILABLE) {
                VehicleModel vehicleModel = vehicleRepository.findById(model.getVehicleID())
                        .orElseThrow(() -> new ResourceNotFoundException("Vehicle does not exist with id: " + model.getVehicleID()));

                DeliveryPersonAllDetailsResponse response = DeliveryPersonAllDetailsResponse.builder()
                        .id(model.getId())
                        .availability(model.getAvailability())
                        .rating((double) model.getTotal_rating() /model.getNo_of_rated_users())
                        .latitude(model.getLatitude())
                        .longitude(model.getLongitude())
                        .lastUpdatedTime(model.getLastUpdatedTime())
                        .color(vehicleModel.getColor())
                        .vehicleNo(vehicleModel.getVehicleNo())
                        .vehicleType(vehicleModel.getVehicleType())
                        .build();

                return ResponseEntity.ok(response);
            }
        }
        return null;
    }
}


