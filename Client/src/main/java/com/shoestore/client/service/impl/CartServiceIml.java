package com.shoestore.client.service.impl;

import com.shoestore.client.dto.request.CartItemDTO;
import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CartServiceIml implements CartService {

  private static final String SERVER_BASE_URL = "http://localhost:8080/cart/";

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<CartItemDTO> getCartItemsByCartId(int cartId) {
    String url = SERVER_BASE_URL + cartId;

    CartItemDTO[] cartItems = restTemplate.getForObject(url, CartItemDTO[].class);
    return cartItems != null ? Arrays.asList(cartItems) : List.of();
  }

  @Override
  public CartItemDTO addCartItem(CartItemDTO cartItemDTO) {
    String apiUrl = "http://localhost:8080/cart/add";
    ResponseEntity<CartItemDTO> response=restTemplate.postForEntity(
            apiUrl,cartItemDTO,CartItemDTO.class
    );
    return response.getBody();
  }

}
