package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.CartItem;
import com.shoestore.Server.repositories.CartItemRepository;
import com.shoestore.Server.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

  private final CartItemRepository cartItemRepository;

  public CartServiceImpl(CartItemRepository cartItemRepository) {
    this.cartItemRepository = cartItemRepository;
  }

  @Override
  public List<CartItem> getCartItemsByCartId(int cartId) {
    return cartItemRepository.findCartItemsByCartId(cartId);
  }
}


