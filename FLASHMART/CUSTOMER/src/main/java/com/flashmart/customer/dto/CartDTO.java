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
    private int noOfItem;
    private double totalPrice;
    private Customer customer;
    private List<Item> items;
}
