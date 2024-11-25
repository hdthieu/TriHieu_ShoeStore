package com.shoestore.Server.repositories;

import com.shoestore.Server.entities.Product;
import com.shoestore.Server.entities.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductID(int productID);
}
