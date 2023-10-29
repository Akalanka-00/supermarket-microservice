package com.flashmart.delivery.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "deliver")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliverModel {

    @Id
    private String id;
    private double latitude;
    private double longitude;
    private int availability;
}
