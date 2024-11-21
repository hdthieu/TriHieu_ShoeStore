package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.Brand;
import com.shoestore.Server.repositories.BrandRepository;
import com.shoestore.Server.service.BrandService;
import org.springframework.stereotype.Service;

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
}
