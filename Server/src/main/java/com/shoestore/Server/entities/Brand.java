package com.shoestore.Server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brandID")
    private int brandID;
    private String name;
    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
