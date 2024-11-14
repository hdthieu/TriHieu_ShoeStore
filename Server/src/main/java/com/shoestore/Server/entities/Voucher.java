package com.shoestore.Server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucherID")
    private int voucherID;
    private String name;
    private String description;
    @Column(name = "startDate")
    private LocalDate startDate;
    @Column(name = "endDate")
    private LocalDate endDate;
    @Column(name = "discountType")
    private String discountType;
    @Column(name = "discountValue")
    private double discountValue;
    private String status;
    @OneToOne(mappedBy = "voucher",cascade = CascadeType.ALL)
    private Order order;
}
