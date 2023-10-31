package com.flashmart.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class orderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderid;
    private String cusid;
    private String deliver_address;
    private Float price;
    private Float deliver_cost;
    private Boolean order_status;
    private String ordered_date;
    private String ordered_time;
    private String paymentid;
    private String deliverid;

    public Boolean isOrder_status() {
        return order_status;
    }

}
