package com.flashmart.delivery.dto;

import com.mongodb.internal.connection.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryEntryRequest {
    private String orderId;
    private Date pickedUpTime;
    private date deliveredTime;


}
