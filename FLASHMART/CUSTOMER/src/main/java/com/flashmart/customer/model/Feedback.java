package com.flashmart.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long refNo;

    private int rating;

    private String description;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @JsonIgnore
    public Customer getCustomer() { return customer; }

    @JsonIgnore
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Long getCustomerId() { return customer.getCustomerId(); }
}
