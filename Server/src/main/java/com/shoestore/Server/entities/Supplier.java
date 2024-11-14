package com.shoestore.Server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierID")
    private int supplierID;
    private String supplierName;
    private String address;
    private String phoneNumber;
    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
    @ElementCollection
    @CollectionTable(name = "Supplier_PhoneNumber",
            joinColumns = @JoinColumn(name = "supplierID"))
    @Column(name = "phoneNumbers")
    private List<String> phoneNumbers;
}
