package com.shoestore.Server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table
@Data
public class CartItem {
    @EmbeddedId
    private CartItemKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cartId")
    @JoinColumn(name = "cartID")
    @JsonBackReference
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    @JoinColumn(name = "productID")
    private Product product;

    private int quantity;
    private double subTotal;
}
