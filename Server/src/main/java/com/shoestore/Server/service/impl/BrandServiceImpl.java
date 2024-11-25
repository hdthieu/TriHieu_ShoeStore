package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.Brand;
import com.shoestore.Server.repositories.BrandRepository;
import com.shoestore.Server.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand getBrand(int id) {
        return brandRepository.findByBrandID(id);
    }
    public List<Brand> getAllBrand() {

        return brandRepository.findAll();
    }
}
