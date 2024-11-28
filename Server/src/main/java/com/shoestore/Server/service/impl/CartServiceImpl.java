package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.Cart;
import com.shoestore.Server.repositories.CartRepository;
import com.shoestore.Server.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    @Override
    public Cart getCartById(int id) {
        return cartRepository.findById(id).orElse(null);
    }
}
