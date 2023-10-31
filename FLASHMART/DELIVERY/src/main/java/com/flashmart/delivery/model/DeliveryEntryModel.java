package com.flashmart.delivery.model;


import com.mongodb.internal.connection.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

@Document(value = "Delivery")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryEntryModel {

    @Id
    private String id;
    private String orderId;
    private Instant pickedUpTime;
    private Instant deliveredTime;

}
