package com.shoestore.client.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoestore.client.dto.request.CartItemDTO;
import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.dto.request.ProductDetailDTO;
import com.shoestore.client.dto.response.CartItemResponseDTO;
import com.shoestore.client.dto.response.ProductDetailCheckoutDTO;
import com.shoestore.client.service.ProductDetailService;
import com.shoestore.client.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CheckoutController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductService productService;
    @GetMapping("/checkout")
    public String checkout(
            @RequestParam("id") List<Integer> productDetailId,
            @RequestParam("quantity") List<Integer> quantities,
            @RequestParam("delivery") int delivery,
            @RequestParam("subToTal") int subTotal,
            @RequestParam("total") int total, Model model) {

        // Map sản phẩm với số lượng
        Map<Integer, Integer> selectedProducts = productDetailId.stream()
                .collect(Collectors.toMap(id -> id, id -> quantities.get(productDetailId.indexOf(id))));

        // Xử lý logic đặt hàng ở đây
        System.out.println("Selected Products: " + selectedProducts);
        List<ProductDetailCheckoutDTO> productDetailCheckoutDTOS = selectedProducts.entrySet().stream()
                .map(entry -> {
                    Integer productDetailsId = entry.getKey();
                    Integer quantity = entry.getValue();

                    // Tìm ProductDetail theo ID
                    ProductDetailDTO productDetail = productDetailService.getProductDetailById(productDetailsId);
                    ProductDTO productDTO=productService.getProductByProductDetail(productDetailsId);
                    // Tạo DTO chứa thông tin cần thiết
                    return new ProductDetailCheckoutDTO(productDetail, quantity,productDTO.getProductName(),productDTO.getImageURL(),productDTO.getPrice());
                })
                .toList();
        productDetailCheckoutDTOS.forEach(item -> {
            System.out.println(item);
        });
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        model.addAttribute("ProductDetailCheckoutDTO",productDetailCheckoutDTOS);
        model.addAttribute("subTotal", formatter.format(subTotal) + " VNĐ");
        model.addAttribute("delivery", delivery == 0 ? "FREE" : formatter.format(delivery) + ".000 VNĐ");
        model.addAttribute("total", formatter.format(total) + " VNĐ");
        // Trả về thông báo thành công
        return "page/Customer/Checkout";
    }
}