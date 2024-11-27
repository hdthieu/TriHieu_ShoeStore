package com.shoestore.Server.repositories;


/*
    @author: Đào Thanh Phú
    Date: 11/20/2024
    Time: 9:24 PM
    ProjectName: Server
*/

import com.shoestore.Server.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
