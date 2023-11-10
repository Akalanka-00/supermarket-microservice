package com.flashmart.order.dto;

import com.flashmart.order.model.orderModel;
import com.flashmart.order.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private orderModel order;

    @ManyToOne
    private Product product; // Reference to the product ordered

    private Integer quantity;
    // Other relevant fields
}
