package com.shoestore.Server.service;


import com.shoestore.Server.entities.CartItem;

import java.util.List;

public interface CartService {
  List<CartItem> getCartItemsByCartId(int cartId);
}

