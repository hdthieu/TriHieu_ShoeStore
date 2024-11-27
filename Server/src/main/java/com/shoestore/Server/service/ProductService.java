package com.shoestore.Server.service;

import com.shoestore.Server.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    public Product saveProduct(Product product);
    public boolean deleteProduct(int id);

    public Product getProductById(int id);


    List<Product> getById(int id);

    // nay cua hieu
    public List<Product> getProductsNotInOrderDetail(int orderID);
}
