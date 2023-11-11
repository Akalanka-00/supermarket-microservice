package com.flashmart.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private long itemCode;
    private String skuCode;
    private double price;
    private int quantity;
    private String itemName;
    private int categoryId;
    private String categoryName;
    private String categoryIcon;
    private int noOfProducts;
    private String productIcon;
}
