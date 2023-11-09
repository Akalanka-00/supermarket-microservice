package com.flashmart.customer.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    private long itemCode;
    private String skuCode;
    private double price;
    private int quantity;
    private String itemName;
    private int categoryId;
    private String categoryName;
    private int noOfProducts;
}
