package com.shoestore.client.service;

import com.shoestore.client.dto.request.CartItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
  List<CartItemDTO> getCartItemsByCartId(int cartId);
  CartItemDTO addCartItem(CartItemDTO cartItemDTO);
}
