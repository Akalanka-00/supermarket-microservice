package com.flashmart.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryPersonAllDetailsResponse {
    private String id;
    private int availability;
    private int rating;
    private String vehicleID;
    private String color;
    private String vehicleNo;
    private String vehicleType;
}
