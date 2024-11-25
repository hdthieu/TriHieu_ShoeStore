package com.shoestore.Server.service;


import com.shoestore.Server.entities.ProductDetail;

import java.util.List;

public interface ProductDetailService {


    public ProductDetail addProductDetail(ProductDetail productDetail);

    List<ProductDetail> getByProductId(int productId);

}
