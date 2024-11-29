package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Brand;
import com.shoestore.Server.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BrandController {
  @Autowired
  private BrandService brandService;

  @GetMapping ("/brands")
  public ResponseEntity<Map<String,Object>> getAllBrands(){
    List<Brand> products = brandService.getAllBrand();
    Map<String,Object> response= new HashMap<>();
    response.put("brandss",products);
    return ResponseEntity.ok(response);
  }
}
