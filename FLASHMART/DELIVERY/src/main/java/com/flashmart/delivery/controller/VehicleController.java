package com.flashmart.delivery.controller;

import com.flashmart.delivery.dto.VehicleRequest;
import com.flashmart.delivery.repository.VehicleRepository;
import com.flashmart.delivery.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void addVehicle(@RequestBody VehicleRequest request){
        vehicleService.addVehicle(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("color/{id}")
    public String updateVehicleColor(@PathVariable String id, @RequestBody VehicleRequest request){
        return vehicleService.updateVehicleColor(id, request);
    }
}
