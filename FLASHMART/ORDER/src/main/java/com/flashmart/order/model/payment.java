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
            cascade =  CascadeType.ALL,
            mappedBy = "payment")
    private orderModel OrderModel;

    private double paymentAmount;
    private String paymentType;
    private LocalDateTime dateAndTime;

    @JsonIgnore
    public orderModel getOrderModel() { return OrderModel;}

    @JsonIgnore
    public void setOrderModel(orderModel orderModel) { OrderModel = orderModel;}
}

