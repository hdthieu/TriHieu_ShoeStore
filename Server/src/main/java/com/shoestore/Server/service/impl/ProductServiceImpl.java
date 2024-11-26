package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.Color;
import com.shoestore.Server.entities.Product;
import com.shoestore.Server.entities.Size;
import com.shoestore.Server.repositories.ProductRepository;
import com.shoestore.Server.service.ProductService;
import com.shoestore.Server.specifications.ProductSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);

    }
    public List<Product> getById(int id) {
        return productRepository.findByProductID(id);

    }

    @Override
    public List<Product> getFilteredProducts(List<Integer> categoryIds, List<Integer> brandIds, List<String> colors, List<String> sizes, Double minPrice, Double maxPrice) {
        Specification<Product> spec = Specification.where(null);

        if (categoryIds != null && !categoryIds.isEmpty()) {
            spec = spec.and(ProductSpecification.hasCategories(categoryIds));
        }

        if (brandIds != null && !brandIds.isEmpty()) {
            spec = spec.and(ProductSpecification.hasBrands(brandIds));
        }
        if (colors != null && !colors.isEmpty()) {
            spec = spec.and(ProductSpecification.hasColors(colors));
        }
        if (sizes != null && !sizes.isEmpty()) {
            spec = spec.and(ProductSpecification.hasSizes(sizes));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpecification.hasMinPrice(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecification.hasMaxPrice(maxPrice));
        }

        return productRepository.findAll(spec);
    }

}
