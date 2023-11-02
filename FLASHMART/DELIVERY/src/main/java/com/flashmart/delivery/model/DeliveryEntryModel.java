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
import java.util.Date;

@Document(value = "DeliveryCollection")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryEntryModel {

    @Id
    private String id;
    private String orderId;
    private Date pickedUpTime;
    private Date deliveredTime;

}
