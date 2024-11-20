package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.ProductDetail;
import com.shoestore.Server.repositories.ProductDetailRepository;
import com.shoestore.Server.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private final ProductDetailRepository productDetailRepository;
    public ProductDetailServiceImpl(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

    @Override
    public List<ProductDetail> getByProductId(int productID) {
        return productDetailRepository.findByProduct_ProductID(productID);
    }
}
