package com.flashmart.delivery.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "deliveryPersonCollection")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryPersonModel {

    @Id
    private String id;
    private int availability;
    private int rating;
    private String vehicleID;

}
