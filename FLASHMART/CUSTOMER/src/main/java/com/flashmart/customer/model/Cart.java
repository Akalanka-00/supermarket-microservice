package com.flashmart.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

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

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @JsonIgnore
    public Customer getCustomer() { return customer; }
    @JsonIgnore
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Long getCustomerId() { return customer.getCustomerId(); }

    public List<CartItem> getCartItems() { return cartItems; }

    public void setCartItems(List<CartItem> cartItems) { this.cartItems = cartItems; }
}
