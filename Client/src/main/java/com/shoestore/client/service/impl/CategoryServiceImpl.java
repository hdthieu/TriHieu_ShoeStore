package com.shoestore.client.service.impl;


/*
    @author: Đào Thanh Phú
    Date: 11/20/2024
    Time: 10:43 PM
    ProjectName: Client
*/


import com.shoestore.client.dto.request.CategoryDTO;
import com.shoestore.client.dto.response.CategoryResponseDTO;
import com.shoestore.client.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<CategoryDTO> getAllCategory() {
        String apiUrl="http://localhost:8080/products/add";
        ResponseEntity<CategoryResponseDTO> response= restTemplate.exchange(
                apiUrl, HttpMethod.GET,null, CategoryResponseDTO.class
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody().getCategoryDTOs();
    }
}
