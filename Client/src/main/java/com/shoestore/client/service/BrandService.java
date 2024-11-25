package com.shoestore.client.service;

import com.shoestore.client.dto.request.BrandDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    public List<BrandDTO> getAllBrand();
}
