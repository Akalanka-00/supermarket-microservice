package com.flashmart.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flashmart.order.model.orderModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @JoinColumn (name = "orderId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private orderModel order;


    private long productId;

    private Integer quantity;

    @JsonIgnore
    public orderModel getOrder() {return order;}

    @JsonIgnore
    public void setOrder(orderModel order) {this.order = order;}

}
