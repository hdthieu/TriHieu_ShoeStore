package com.shoestore.Server.service;


import com.shoestore.Server.entities.ProductDetail;

import java.util.List;

public interface ProductDetailService {


    public ProductDetail addProductDetail(ProductDetail productDetail);

    List<ProductDetail> getByProductId(int productId);

<<<<<<< HEAD

    ProductDetail save(ProductDetail productDetail);

=======
>>>>>>> 2fd106e8114764d9edf80dd5189edb38aefd03ab
}
