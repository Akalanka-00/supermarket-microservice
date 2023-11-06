package com.flashmart.customer.dto;

import com.flashmart.customer.model.Customer;
import com.flashmart.customer.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO {
    private long cartId;
    private int noOfItem;
    private double totalPrice;
    private long customerId;
    private List<CartItemDTO> items;
}
