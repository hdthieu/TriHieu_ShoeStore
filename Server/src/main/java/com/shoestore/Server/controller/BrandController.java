package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Brand;
import com.shoestore.Server.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    @GetMapping("/{id}") // Ánh xạ HTTP GET
    public ResponseEntity<Map<String,Object>> getBrand(@PathVariable int id){
        Brand brand=brandService.getBrand(id);
        Map<String,Object> response= new HashMap<>();
        response.put("brand",brand);
        return ResponseEntity.ok(response);
    }
}
