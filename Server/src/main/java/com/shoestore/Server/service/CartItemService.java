package com.shoestore.Server.service;


import com.shoestore.Server.entities.Cart;
import com.shoestore.Server.entities.CartItem;

import java.util.List;

public interface CartItemService {
  List<CartItem> getCartItemsByCartId(int cartId);
  CartItem addCartItem(CartItem cartItem);
  Cart getCartById(int id);
}

