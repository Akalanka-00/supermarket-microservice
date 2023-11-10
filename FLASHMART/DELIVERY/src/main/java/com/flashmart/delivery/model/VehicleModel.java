package com.flashmart.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "VehicleCollection")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VehicleModel {

    @Id
    private String id;
    private String color;
    private String vehicleNo;
    private String vehicleType;
}
