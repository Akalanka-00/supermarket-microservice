package com.flashmart.delivery.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "delivery")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryModel {

    @Id
    private String id;
    private String deliverId;
    private String orderId;
    private String customerId;
    private int status;

}
