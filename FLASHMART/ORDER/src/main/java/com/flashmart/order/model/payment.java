package com.flashmart.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="payment")
public class payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @OneToOne(fetch = FetchType.LAZY,
            mappedBy = "Payment")
    private orderModel OrderModel;

    private double paymentAmount;
    private String paymentType;
    private LocalDateTime dateAndTime;


    public orderModel getOrderModel() { return OrderModel;}


    public void setOrderModel(orderModel orderModel) { OrderModel = orderModel;}

}

