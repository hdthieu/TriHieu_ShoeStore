package com.shoestore.Server.service;

import com.shoestore.Server.entities.Brand;

import java.util.List;

public interface BrandService {
    Brand getBrand(int id);
    public List<Brand> getAllBrand();
}
