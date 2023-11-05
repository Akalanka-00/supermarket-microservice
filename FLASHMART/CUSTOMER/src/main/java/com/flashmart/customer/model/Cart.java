package com.flashmart.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            name = "cartItems",
            joinColumns = { @JoinColumn(name = "cartId") },
            inverseJoinColumns = { @JoinColumn(name = "itemCode") })
    private List<Item> items;

    @JsonIgnore
    public Customer getCustomer() { return customer; }

    @JsonIgnore
    public void setCustomer(Customer customer) { this.customer = customer; }

    @JsonIgnore
    public List<Item> getItems() { return items; }

    @JsonIgnore
    public void setItems(List<Item> items) { this.items = items; }
}
