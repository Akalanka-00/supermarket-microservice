package com.flashmart.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private long cartId;
    private int noOfItem;
    private double totalPrice;
    private long customerId;
    private double discounts;
    private List<CartItemDTO> items;
}
