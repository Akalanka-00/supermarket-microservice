package com.flashmart.customer.model;

import com.flashmart.customer.dto.CartDTO;
import com.flashmart.customer.dto.ItemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartId;

    private int noOfItem;

    private double totalPrice;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "item",
            joinColumns = { @JoinColumn(name = "cartId") },
            inverseJoinColumns = { @JoinColumn(name = "itemCode") })
    private List<Item> items;

    public Cart(int noOfItem, double totalPrice, Customer customer, List<Item> items) {
        this.noOfItem = noOfItem;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.items = items;
    }

}
