package com.shoestore.Server.repositories;

import com.shoestore.Server.entities.Product;
import com.shoestore.Server.entities.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductID(int productID);

    // này của hieu
    @Query("SELECT p FROM Product p WHERE p.productID NOT IN :productIDs")
    List<Product> findByProductIDNotIn(@Param("productIDs") List<Integer> productIDs);
}
