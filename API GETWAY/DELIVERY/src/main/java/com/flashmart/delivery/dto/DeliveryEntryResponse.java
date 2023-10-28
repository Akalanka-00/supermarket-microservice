package com.flashmart.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryEntryResponse {
    private String id;
    private String deliverId;
    private String orderId;
    private String customerId;
    private int status;

}
