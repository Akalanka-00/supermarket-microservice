package com.flashmart.delivery.dto;

import com.mongodb.internal.connection.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryEntryResponse {
    private String id;
    private String orderId;
    private Instant pickedUpTime;
    private Instant deliveredTime;

}
