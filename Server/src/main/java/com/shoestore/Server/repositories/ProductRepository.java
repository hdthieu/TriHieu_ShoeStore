package com.shoestore.Server.repositories;

import com.shoestore.Server.entities.Color;
import com.shoestore.Server.entities.Product;
import com.shoestore.Server.entities.ProductDetail;
import com.shoestore.Server.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> , JpaSpecificationExecutor<Product> {
    List<Product> findByProductID(int productID);
    @Query("SELECT DISTINCT p FROM Product p " +
            "JOIN p.productDetails pd " +
            "WHERE (:category IS NULL OR p.category.categoryID = :category) " +
            "AND (:brand IS NULL OR p.brand.brandID = :brand) " +
            "AND (:color IS NULL OR pd.color = :color) " +
            "AND (:size IS NULL OR pd.size = :size) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<Product> findProductsByFilters(
            @Param("category") Integer category,
            @Param("brand") Integer brand,
            @Param("color") Color color,
            @Param("size") Size size,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );
}
