package com.shoestore.Server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewID")
    private int reviewID;
    private String contents;
    private int start;
    private LocalDate createDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productID")
    private Product product;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID")
    private User user;
}
