package com.flashmart.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryPersonAllDetailsResponse {
    private String id;
    private int availability;
    private double rating;
    private double latitude;
    private double longitude;
    private Date lastUpdatedTime;
    private String color;
    private String vehicleNo;
    private String vehicleType;
}
