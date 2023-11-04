package com.flashmart.customer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemCode;

    private String itemName;

    private int quantity;

    private double price;

    private String category;

    @ManyToMany(mappedBy = "releases")
    private Set<Cart> cart = new HashSet<>();

}
