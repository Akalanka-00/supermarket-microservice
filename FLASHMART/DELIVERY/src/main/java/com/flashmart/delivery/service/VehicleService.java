package com.flashmart.delivery.service;

import com.flashmart.delivery.dto.VehicleRequest;
import com.flashmart.delivery.dto.VehicleResponse;
import com.flashmart.delivery.model.DeliveryPersonModel;
import com.flashmart.delivery.model.VehicleModel;
import com.flashmart.delivery.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public void addVehicle(VehicleRequest request){
        VehicleModel model = VehicleModel.builder()
                .color(request.getColor())
                .vehicleNo(request.getVehicleNo())
                .vehicleType(request.getVehicleType())
                .build();

        vehicleRepository.save(model);
    }

    public String updateVehicleColor(String id, VehicleRequest request){
        VehicleModel model = vehicleRepository.findById(id).orElse(null);
        if(model!=null){
            if (request.getColor()!=null){
                model.setColor(request.getColor());
            }
            vehicleRepository.save(model);
            return ("Vehicle color is Changed to "+request.getColor());
        }
        return "Error occurred";
    }

    public List<VehicleModel> getAllDeliveryPerson() {
        return vehicleRepository.findAll();
    }
}
