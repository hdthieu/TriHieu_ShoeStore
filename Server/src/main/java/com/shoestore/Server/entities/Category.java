package com.shoestore.Server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryID")
    private int categoryID;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
