package com.flashmart.inventory.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product_Table")

public class Product {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private long itemCode;

    @Column(name = "Sku_Code")
    private String skuCode;

    @Column(name = "Price")
    private double price;

    @Column(name = "Quantity")
    private String quantity;

    @Column(name = "Item_Name")
    private String itemName;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_icon")
    private String categoryIcon;

    @Column(name = "no_of_products")
    private int noOfProducts;

    @Column(name = "product_icon")
    private String productIcon;
}
