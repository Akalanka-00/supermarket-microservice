package com.flashmart.order.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@SuperBuilder
public class OrderObject {
     Long orderid;
     String cusid;
     String deliver_address;
     Float price;
     Float deliver_cost;
     Boolean order_status;
     String ordered_date;
     String ordered_time;
}
