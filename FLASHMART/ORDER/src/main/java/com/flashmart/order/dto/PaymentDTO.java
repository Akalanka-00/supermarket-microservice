package com.flashmart.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private int paymentId;
    private int orderId;
    private double paymentAmount;
    private String paymentType;
    private LocalDateTime dateAndTime;
}