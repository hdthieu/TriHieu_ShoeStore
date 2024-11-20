package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Product;
import com.shoestore.Server.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping // Ánh xạ HTTP GET
    public ResponseEntity<Map<String,Object>> getAllProducts(){
        List<Product> products=productService.getAllProduct();
        Map<String,Object> response= new HashMap<>();
        response.put("products",products);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}") // Ánh xạ HTTP GET
    public ResponseEntity<Map<String,Object>> getProductsById(@PathVariable int id){
        List<Product> products=productService.getById(id);
        Map<String,Object> response= new HashMap<>();
        response.put("product",products);
        return ResponseEntity.ok(response);
    }
}
