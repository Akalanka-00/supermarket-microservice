package com.flashmart.delivery.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(value = "deliveryPersonCollection")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryPersonModel {

    @Id
    private String id;
    private int userID;
    private int availability;
    private String vehicleID;
    private double latitude;
    private double longitude;
    private Date lastUpdatedTime;
    private int total_rating;
    private int no_of_rated_users;
}
