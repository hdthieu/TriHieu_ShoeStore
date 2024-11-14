package com.shoestore.Server.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class OrderDetail {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderID")
    private Order order;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID")
    private Product product;
    private int quantity;
    private double price;
}
