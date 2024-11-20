package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Product;
import com.shoestore.Server.entities.ProductDetail;
import com.shoestore.Server.service.ProductDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("products-details")
public class ProductDetailController {
    private ProductDetailService productDetailService;

    public ProductDetailController(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getProductDetailsByProductId(@PathVariable int id) {
        List<ProductDetail> productDetails=productDetailService.getByProductId(id);
        Map<String,Object> response= new HashMap<>();
        response.put("productDetails",productDetails);
        return ResponseEntity.ok(response);
    }

}
