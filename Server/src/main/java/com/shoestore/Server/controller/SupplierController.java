package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Category;
import com.shoestore.Server.entities.Supplier;
import com.shoestore.Server.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
    @GetMapping("/{id}") // Ánh xạ HTTP GET
    public ResponseEntity<Map<String,Object>> getSupplier(@PathVariable int id){
        Supplier supplier=supplierService.getSupplier(id);
        Map<String,Object> response= new HashMap<>();
        response.put("supplier",supplier);
        return ResponseEntity.ok(response);
    }
}
