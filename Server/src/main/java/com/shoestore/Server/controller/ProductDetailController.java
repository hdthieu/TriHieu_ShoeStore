package com.shoestore.Server.controller;


/*
    @author: Đào Thanh Phú
    Date: 11/22/2024
    Time: 3:25 PM
    ProjectName: Server
*/


import com.shoestore.Server.entities.Product;
import com.shoestore.Server.entities.ProductDetail;
import com.shoestore.Server.service.ProductDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/details")
public class ProductDetailController {
    private final ProductDetailService productDetailService;

    public ProductDetailController(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    @PostMapping
    public ResponseEntity<ProductDetail> addProductDetail(@RequestBody ProductDetail productDetail) {
        try {
            // Lưu sản phẩm vào cơ sở dữ liệu
            ProductDetail savedProductDetail = productDetailService.addProductDetail(productDetail);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDetail);
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
