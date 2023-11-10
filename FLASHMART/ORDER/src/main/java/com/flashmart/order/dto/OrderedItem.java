package com.flashmart.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flashmart.order.model.orderModel;
import com.flashmart.order.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @JoinColumn (name = "orderId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private orderModel order;

    @JsonIgnore
    @JoinColumn (name = "productId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private Product product; // Reference to the product ordered

    private Integer quantity;

    @JsonIgnore
    public orderModel getOrder() {return order;}

    @JsonIgnore
    public void setOrder(orderModel order) {this.order = order;}

    @JsonIgnore
    public Product getProduct() {return product;}

    @JsonIgnore
    public void setProduct(Product product) {this.product = product;}
}
