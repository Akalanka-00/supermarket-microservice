package com.flashmart.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flashmart.order.dto.OrderedItem;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class orderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderid;

    private long cusid;
    private String deliver_address;
    private double price;
    private double deliver_cost;
    private Boolean order_status;
    private String ordered_date;
    private String ordered_time;


    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentId")
    private payment Payment;

    private String deliverid;

    @OneToMany(mappedBy = "order")
    private List<OrderedItem> orderedItems;

    public Boolean isOrder_status() {
        return order_status;
    }


    public payment getPayment() { return Payment; }


    public void setPayment(payment payment) { Payment = payment; }

    public long getPaymentid() { return Payment.getPaymentId(); }


    public List<OrderedItem> getOrderedItems() { return orderedItems; }

    public void setOrderedItems(List<OrderedItem> orderedItems) { this.orderedItems = orderedItems;}
}
