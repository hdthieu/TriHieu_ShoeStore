package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/product-search")
    public String showProducts(Model model){
        List<ProductDTO> products= productService.getAllProduct();
        model.addAttribute("products",products);
        System.out.println(products);
        return "page/Customer/Search";
    }
}
