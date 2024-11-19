package com.shoestore.client.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private int productID;
    private String productName;
    private List<String> imageURL;
    private String description;
    private double price;
    private String status;
    private BrandDTO brand;
    private CategoryDTO category;
    private SupplierDTO supplier;
//    private PromotionDTO promotionDTO;
}