package com.shoestore.Server.entities;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productDetailID")
    private int productDetailID;
    private Color color;
    private Size size;
    @Column(name = "stockQuantity")
    private int stockQuantity;
    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;
}
