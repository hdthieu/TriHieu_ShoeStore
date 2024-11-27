package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Cart;
import com.shoestore.Server.entities.CartItem;
import com.shoestore.Server.entities.Voucher;
import com.shoestore.Server.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<List<CartItem>> getCartItemsByCartId(@PathVariable int id) {
    List<CartItem> cartItems = cartService.getCartItemsByCartId(id);
    return ResponseEntity.ok(cartItems);
  }
  @PostMapping("/add")
  public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItem) {
    CartItem cartItemAdded = cartService.addCartItem(cartItem);
    return ResponseEntity.ok(cartItemAdded);
  }

}

