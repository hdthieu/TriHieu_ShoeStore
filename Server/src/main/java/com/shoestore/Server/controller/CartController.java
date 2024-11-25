package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Cart;
import com.shoestore.Server.entities.CartItem;
import com.shoestore.Server.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping("/items")
  public ResponseEntity<List<CartItem>> getCartItems(@RequestParam int cartId) {
    List<CartItem> cartItems = cartService.getCartItemsByCartId(cartId);
    return ResponseEntity.ok(cartItems);
  }
}

