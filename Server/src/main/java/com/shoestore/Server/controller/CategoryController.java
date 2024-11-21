package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Brand;
import com.shoestore.Server.entities.Category;
import com.shoestore.Server.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/{id}") // Ánh xạ HTTP GET
    public ResponseEntity<Map<String,Object>> getCategory(@PathVariable int id){
        Category category=categoryService.getCategory(id);
        Map<String,Object> response= new HashMap<>();
        response.put("category",category);
        return ResponseEntity.ok(response);
    }
}
