package com.shoestore.client.service.impl;


import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.dto.response.ProductFindDTO;
import com.shoestore.client.dto.response.ProductResponseDTO;
import com.shoestore.client.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
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


    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        String apiUrl = "http://localhost:8080/products/add";
        ResponseEntity<ProductDTO> response = restTemplate.postForEntity(
                apiUrl, productDTO, ProductDTO.class
        );
        return response.getBody();
    }

    @Override
    public ProductDTO getProductByIdForDetail(int id) {
        String apiUrl="http://localhost:8080/products/detailFor/"+id;
        ResponseEntity<ProductDTO> response= restTemplate.exchange(
                apiUrl, HttpMethod.GET,null, ProductDTO.class
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody();
    }

    public ProductDTO getProductById(int id) {
        String apiUrl = "http://localhost:8080/products/{id}";
        ResponseEntity<ProductFindDTO> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, ProductFindDTO.class, id
        );
        System.out.println("Response Body: " + response.getBody());

        if (response.getBody() != null && response.getBody().getProductDTOs() != null && !response.getBody().getProductDTOs().isEmpty()) {
            return response.getBody().getProductDTOs().get(0);
        }
        return null;
    }

    @Override
    public List<ProductDTO> getFilteredProducts(List<Integer> categoryIds, List<Integer> brandIds, List<String> color, List<String> size, Double minPrice, Double maxPrice, String sortBy) {
        StringBuilder apiUrl = new StringBuilder("http://localhost:8080/products/filtered");

        // Thêm các tham số vào URL nếu không null
        boolean hasParam = false;
        if (categoryIds != null && !categoryIds.isEmpty()) {
            apiUrl.append(hasParam ? "&" : "?").append("categoryIds=")
                    .append(String.join(",", categoryIds.stream().map(String::valueOf).toArray(String[]::new))); // Sử dụng categoryIds thay vì category
            hasParam = true;
        }
        if (brandIds != null && !brandIds.isEmpty()) {
            apiUrl.append(hasParam ? "&" : "?").append("brandIds=")
                    .append(String.join(",", brandIds.stream().map(String::valueOf).toArray(String[]::new))); // Sử dụng brandIds thay vì brand
            hasParam = true;
        }
        if (color != null && !color.isEmpty()) {
            apiUrl.append(hasParam ? "&" : "?").append("colors=")
                    .append(String.join(",", color));
            hasParam = true;
        }
        if (size != null && !size.isEmpty()) {
            apiUrl.append(hasParam ? "&" : "?").append("size=")
                    .append(String.join(",", size));
            hasParam = true;
        }
        if (minPrice != null) {
            apiUrl.append(hasParam ? "&" : "?").append("minPrice=").append(minPrice);
            hasParam = true;
        }
        if (maxPrice != null) {
            apiUrl.append(hasParam ? "&" : "?").append("maxPrice=").append(maxPrice);
        }
        if (sortBy != null) {
            try {
                // URL encode the sortBy parameter to handle special characters
                String encodedSortBy = URLEncoder.encode(sortBy, StandardCharsets.UTF_8.toString());
                apiUrl.append(hasParam ? "&" : "?").append("sortBy=").append(encodedSortBy);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();  // Handle exception if encoding fails
            }
        }
        System.out.println(apiUrl);
        String rawJson = restTemplate.getForObject(apiUrl.toString(), String.class);
        System.out.println("Raw JSON from API (before parsing): " + rawJson);
//        ResponseEntity<List<ProductDTO>> response = restTemplate.exchange(
//                apiUrl.toString(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<ProductDTO>>() {}
//        );
//        List<ProductDTO> products = response.getBody();
//        System.out.println(products.size());
//        System.out.println("Dữ liệu từ API (trước khi xử lý): " + response.getBody());
        // Trả về danh sách sản phẩm từ response body
//        if (response.getBody() != null) {
//            return response.getBody().getProductDTOs();
//        }

        // Trả về danh sách rỗng nếu không có sản phẩm nào
        return new LinkedList<>();
    }







}
