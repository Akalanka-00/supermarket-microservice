package com.flashmart.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliveryEntryRequest {
    private String orderId;
    private Date pickedUpTime;
    private Date deliveredTime;
}
