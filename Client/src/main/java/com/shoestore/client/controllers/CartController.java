package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.CartItemDTO;
import com.shoestore.client.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class CartController {

  @Autowired
  private CartService cartService;

  @GetMapping("/cart")
  public String showCart(@RequestParam("cartId") int cartId, Model model) {
    List<CartItemDTO> cartItems = cartService.getCartItemsByCartId(cartId);
    model.addAttribute("cartItems", cartItems);

    System.out.println(cartItems);
    cartItems.forEach((item) -> {
      if (item.getProduct() != null && item.getProduct().getProductDetails() != null) {
        item.setProductDetails(item.getProduct().getProductDetails());
      }
    });

    return "page/Customer/Cart";
  }
}


