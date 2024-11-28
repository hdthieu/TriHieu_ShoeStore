package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.dto.request.ProductDetailDTO;
import com.shoestore.client.dto.response.CartItemResponseDTO;
import com.shoestore.client.service.CartItemService;
import com.shoestore.client.service.ProductDetailService;
import com.shoestore.client.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDetailService productDetailService;
    @GetMapping("/cart/{id}")
    public String showCart(@PathVariable("id") int id, Model model) {
        List<CartItemResponseDTO> cartItems = cartItemService.getCartItemsByCartId(id);
        cartItems.forEach((item) -> {
            ProductDTO productDTO = productService.getProductByProductDetail(item.getId().getProductDetailId());
            ProductDetailDTO productDetailDTO = productDetailService.getProductDetailById(item.getId().getProductDetailId());
            item.setProductName(productDTO.getProductName());
            item.setProductImage(productDTO.getImageURL());
            item.setProductPrice(productDTO.getPrice());
            item.setColor(productDetailDTO.getColor());
            item.setSize(productDetailDTO.getSize());
            item.setStockQuantity(productDetailDTO.getStockQuantity());
//            item.set
//            System.out.println(productDetailDTOS);
            System.out.println(item);

        });
        model.addAttribute("cartItems", cartItems);
//        return "Chào";
        return "page/Customer/Cart";
    }

//    @PostMapping("/cart/add")
//    public String addCartItem(CartItemResponseDTO cartItemDTO, Model model) {
//        try {
//            // Gửi sản phẩm mới đến server để lưu
//            CartItemResponseDTO cartSave = cartService.addCartItem(cartItemDTO);
//            model.addAttribute("message", "Sản phẩm đã được thêm thành công vào giỏ hàng!");
//            return "Thêm thành công"; //Chỗ này k chuyển mà hiện modal nè
//        } catch (Exception e) {
//            model.addAttribute("error", "Có lỗi xảy ra khi thêm sản phẩm: " + e.getMessage());
//            return "page/Customer/ProductDetail";
//        }
//    }
}


