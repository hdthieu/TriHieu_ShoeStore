package com.shoestore.client.service;



import com.shoestore.client.dto.request.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    List<ProductDTO> getAllProduct();
}
