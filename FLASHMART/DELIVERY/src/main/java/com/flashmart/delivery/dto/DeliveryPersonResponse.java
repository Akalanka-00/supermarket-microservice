package com.flashmart.delivery.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryPersonResponse {

    private String id;
    private int availability;
    private int rating;
    private String vehicleID;
}
