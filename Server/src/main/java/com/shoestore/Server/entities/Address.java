package com.shoestore.Server.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressID")
    private int addressID;
    private String street;
    private String city;
    private String ward;
    private String district;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;


}