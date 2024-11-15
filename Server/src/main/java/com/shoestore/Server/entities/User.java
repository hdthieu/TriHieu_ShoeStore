package com.shoestore.Server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private int userID;
    private String name;
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Address> addresses;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    private String password;
    @Column(name = "userName")
    private String userName;
    private String CI;
    private String status;
    @ManyToOne
    @JoinColumn(name = "roleID")
    private Role role;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Cart cart;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;
}
