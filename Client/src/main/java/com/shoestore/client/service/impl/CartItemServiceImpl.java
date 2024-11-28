package com.shoestore.client.service.impl;

import com.shoestore.client.dto.response.CartItemResponseDTO;
import com.shoestore.client.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

  private static final String SERVER_BASE_URL = "http://localhost:8080/cart/";

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<CartItemResponseDTO> getCartItemsByCartId(int cartId) {
    String url = SERVER_BASE_URL + cartId;

    CartItemResponseDTO[] cartItems = restTemplate.getForObject(url, CartItemResponseDTO[].class);
    return cartItems != null ? Arrays.asList(cartItems) : List.of();
  }

  @Override
  public CartItemResponseDTO addCartItem(CartItemResponseDTO cartItemDTO) {
    String apiUrl = "http://localhost:8080/cart/add";
    ResponseEntity<CartItemResponseDTO> response=restTemplate.postForEntity(
            apiUrl,cartItemDTO, CartItemResponseDTO.class
    );
    return response.getBody();
  }

}
