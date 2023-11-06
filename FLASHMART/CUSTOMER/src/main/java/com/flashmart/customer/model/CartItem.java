package com.flashmart.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;

    @JsonIgnore
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "cartId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cart cart;

    @JsonIgnore
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "itemCode", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Item item;

    private int quantity;

    @JsonIgnore
    public Item getItem() { return item; }
    @JsonIgnore
    public void setItem(Item item) { this.item = item; }

    @JsonIgnore
    public Cart getCart() { return cart; }
    @JsonIgnore
    public void setCart(Cart cart) { this.cart = cart; }
}
