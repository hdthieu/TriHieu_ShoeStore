package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.*;
import com.shoestore.client.dto.response.CartItemResponseDTO;
import com.shoestore.client.service.CartItemService;
import com.shoestore.client.service.CartService;
import com.shoestore.client.service.ProductDetailService;
import com.shoestore.client.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private HttpSession session;

    @GetMapping("/cart")
    public String showCart(Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        // Lấy thông tin user từ session
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Nếu không có user trong session, chuyển hướng về trang đăng nhập
        }

        // Dùng userId để lấy giỏ hàng của người dùng
        int userId = user.getUserID();
        List<CartItemResponseDTO> cartItems = cartItemService.getCartItemsByCartId(userId);
        cartItems.forEach((item) -> {
            ProductDTO productDTO = productService.getProductByProductDetail(item.getId().getProductDetailId());
            ProductDetailDTO productDetailDTO = productDetailService.getProductDetailById(item.getId().getProductDetailId());
            item.setProductName(productDTO.getProductName());
            item.setProductImage(productDTO.getImageURL());
            item.setProductPrice(productDTO.getPrice());
            item.setColor(productDetailDTO.getColor());
            item.setSize(productDetailDTO.getSize());
            item.setStockQuantity(productDetailDTO.getStockQuantity());
        });
        model.addAttribute("cartItems", cartItems);
        return "page/Customer/Cart";
    }

    @PostMapping("/cart/add")
    public String addCartItem(@RequestParam("productDetailID") int productDetailID,
                              @RequestParam("quantity") int quantity,
//                              CartItemResponseDTO cartItemDTO,
                              Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        // Lấy thông tin user từ session
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Nếu không có user trong session, chuyển hướng về trang đăng nhập
        }

        // Dùng userId để lấy giỏ hàng của người dùng
        int userId = user.getUserID();
        CartItemDTO cartItemDTO = new CartItemDTO();
        CartDTO cartDTO = cartService.getCartByUserId(userId);
        CartItemDTO.IdDTO idDTO = new CartItemDTO.IdDTO(cartDTO.getCartID(), productDetailID);
        cartItemDTO.setId(idDTO);
        cartItemDTO.setQuantity(quantity);
        ProductDTO productDTO = productService.getProductByProductDetail(productDetailID);
        double price = productDTO.getPrice();
        double subTotal = price * quantity;
        cartItemDTO.setSubTotal(subTotal);
        ProductDetailDTO productDetailDTO = productDetailService.getProductDetailById(productDetailID);
        cartItemDTO.setCart(cartDTO);
        cartItemDTO.setProductDetailDTO(productDetailDTO);
        CartItemDTO cartItemSave = cartItemService.addCartItem(cartItemDTO);
        if (cartItemSave != null) {
            return "redirect:/cart";

        } else {
            return "page/Customer/ProductDetail";
        }


    }
}


