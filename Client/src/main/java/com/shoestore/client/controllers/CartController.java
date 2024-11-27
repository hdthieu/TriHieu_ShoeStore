package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.CartItemDTO;
import com.shoestore.client.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart/{id}")
    public String showCart(@PathVariable("id") int id, Model model) {
        List<CartItemDTO> cartItems = cartService.getCartItemsByCartId(id);
        model.addAttribute("cartItems", cartItems);

        System.out.println(cartItems);
        cartItems.forEach((item) -> {
            if (item.getProduct() != null && item.getProduct().getProductDetails() != null) {
                item.setProductDetails(item.getProduct().getProductDetails());
                System.out.println(item.getProductDetails());
            }
        });

        return "page/Customer/Cart";
    }

    @PostMapping("/cart/add")
    public String addCartItem(CartItemDTO cartItemDTO, Model model) {
        try {
            // Gửi sản phẩm mới đến server để lưu
            CartItemDTO cartSave = cartService.addCartItem(cartItemDTO);
            model.addAttribute("message", "Sản phẩm đã được thêm thành công vào giỏ hàng!");
            return "Thêm thành công"; //Chỗ này k chuyển mà hiện modal nè
        } catch (Exception e) {
            model.addAttribute("error", "Có lỗi xảy ra khi thêm sản phẩm: " + e.getMessage());
            return "page/Customer/ProductDetail";
        }
    }
}


