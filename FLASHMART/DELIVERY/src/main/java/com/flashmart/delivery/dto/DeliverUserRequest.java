package com.flashmart.delivery.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliverUserRequest {

    private String id;
    private double latitude;
    private double longitude;
    private int availability;
}
