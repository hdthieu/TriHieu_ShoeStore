package com.shoestore.Server.repositories;


/*
    @author: Đào Thanh Phú
    Date: 11/22/2024
    Time: 2:29 PM
    ProjectName: Server
*/

import com.shoestore.Server.entities.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
}
