package com.flashmart.order.model;
import com.flashmart.order.dto.OrderedItem;

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


    @OneToMany(mappedBy = "order")
    private List<OrderedItem> orderedItems;

    public Boolean isOrder_status() {
        return order_status;
    }

}
