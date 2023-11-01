package com.flashmart.delivery.service;

import com.flashmart.delivery.dto.VehicleRequest;
import com.flashmart.delivery.model.VehicleModel;
import com.flashmart.delivery.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
