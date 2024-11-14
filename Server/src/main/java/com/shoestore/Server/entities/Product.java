package com.shoestore.Server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    private int productID;
    @Column(name = "productName")
    private String productName;
    @ElementCollection
    @CollectionTable(name = "Product_ImageURL", // tên bảng phụ
            joinColumns = @JoinColumn(name = "productID")) // khoá ngoại trỏ đến bảng chính
    @Column(name = "imageURL") // tên cột lưu chuỗi
    private List<String> imageURL;
    private String description;
    private double price;
    private String status;
    @ManyToOne
    @JoinColumn(name = "brandID")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "supplierID")
    private Supplier supplier;
    @OneToMany(mappedBy = "product")
    private List<ProductDetail> productDetails;
    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

}
