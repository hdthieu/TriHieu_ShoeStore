package com.shoestore.Server.service;

import com.shoestore.Server.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    public Product saveProduct(Product product);
    public boolean deleteProduct(int id);

    public Product getProductById(int id);
    List<Product> getById(int id);
    List<Product> getFilteredProducts(List<Integer> categoryIds, List<Integer> brandIds, List<String> colors, List<String> sizes, Double minPrice, Double maxPrice,String sortBy);
}
