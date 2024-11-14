package com.shoestore.Server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartID")
    private int cartID;
    private LocalDateTime createAt;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID")
    private User user;
}
