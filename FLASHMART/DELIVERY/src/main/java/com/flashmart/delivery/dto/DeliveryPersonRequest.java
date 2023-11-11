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
public class DeliveryPersonRequest {


    private int availability;
    private String vehicleID;
    private double latitude;
    private double longitude;
    private Date lastUpdatedTime;
    private int total_rating;
    private int no_of_rated_users;
}
