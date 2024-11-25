package com.shoestore.Server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table
public class CartItem {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartID")
    private Cart cart;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID")
    @JsonBackReference
    private Product product;
    private int quantity;
    private double subTotal;
}
