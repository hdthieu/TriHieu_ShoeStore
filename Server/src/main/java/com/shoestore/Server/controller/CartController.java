package com.shoestore.Server.controller;

import com.shoestore.Server.entities.*;
import com.shoestore.Server.service.CartItemService;
import com.shoestore.Server.service.ProductDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

  private final CartItemService cartItemService;
private final ProductDetailService productDetailService;

  public CartController(CartItemService cartService, CartItemService cartItemService, ProductDetailService productDetailService) {
      this.cartItemService = cartItemService;
      this.productDetailService = productDetailService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<List<CartItem>> getCartItemsByCartId(@PathVariable int id) {
    List<CartItem> cartItems = cartItemService.getCartItemsByCartId(id);
    return ResponseEntity.ok(cartItems);
  }
  @PostMapping("/add")
  public ResponseEntity<?> addCartItem(@RequestBody CartItem cartItem) {
    try {
      CartItem savedCartItem = cartItemService.addCartItem(cartItem);
      return ResponseEntity.ok(savedCartItem);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
  }


}

