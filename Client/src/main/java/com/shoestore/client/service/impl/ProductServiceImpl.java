package com.shoestore.client.service.impl;


import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.dto.response.ProductResponseDTO;
import com.shoestore.client.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<ProductDTO> getAllProduct() {
        String apiUrl="http://localhost:8080/products";
        ResponseEntity<ProductResponseDTO> response= restTemplate.exchange(
                apiUrl, HttpMethod.GET,null, ProductResponseDTO.class
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody().getProductDTOs();
    }
}
