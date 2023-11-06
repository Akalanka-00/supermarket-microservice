package com.flashmart.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemDTO {

    private long itemCode;
    private String itemName;
    private double price;
    private int quantity;
}
