package com.shoestore.Server.service.impl;


/*
    @author: Đào Thanh Phú
    Date: 11/22/2024
    Time: 2:32 PM
    ProjectName: Server
*/


import com.shoestore.Server.entities.ProductDetail;
import com.shoestore.Server.repositories.ProductDetailRepository;
import com.shoestore.Server.service.ProductDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    private final ProductDetailRepository productDetailRepository;

    public ProductDetailServiceImpl(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }


    @Override
    public ProductDetail addProductDetail(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }
}
