package com.shoestore.Server.service;

import com.shoestore.Server.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    List<Product> getById(int id);

    // nay cua hieu
    public List<Product> getProductsNotInOrderDetail(int orderID);
}
