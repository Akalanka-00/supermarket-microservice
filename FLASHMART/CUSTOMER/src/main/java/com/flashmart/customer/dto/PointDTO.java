package com.flashmart.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PointDTO {

    private double discount;

    private double points;

    private long customerId;
}
