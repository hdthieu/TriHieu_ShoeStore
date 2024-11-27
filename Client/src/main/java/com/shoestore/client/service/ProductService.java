package com.shoestore.client.service;



import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.dto.request.ProductHomeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    List<ProductDTO> getAllProduct();

    public ProductDTO addProduct(ProductDTO productDTO);
    public ProductDTO getProductByIdForDetail(int id);
    public ProductDTO getProductById(int id);
    //  Home
    public List<ProductHomeDTO> getTop10BestSellers();

    public List<ProductHomeDTO> getTop10NewArrivals();

    public List<ProductHomeDTO> getTop10Trending();
}
