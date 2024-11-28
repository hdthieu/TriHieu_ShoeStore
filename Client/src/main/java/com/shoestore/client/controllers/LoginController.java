package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.UserDTO;
import com.shoestore.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

  @Autowired
  private UserService userService;

  // Đăng nhập
  @GetMapping("/login")
  public String login() {
    return "page/Login";  //
  }

  @PostMapping("/login/auth")
  public String login(@ModelAttribute UserDTO userDTO, Model model) {
    UserDTO user = userService.findByEmail(userDTO.getEmail());
    if (user != null && userDTO.getPassword().equals(user.getPassword())) {
//      model.addAttribute("user", user);

      if ("Admin".equals(user.getRole().getName())) {
        System.out.println(user.getRole().getName());
        return "page/Admin/TrangChuQuanLy";
      } else if ("Customer".equals(user.getRole().getName())) {
        return "page/Customer/Home";
      }
    }
    return "redirect:/page/Login?error";
  }


  // Trang chính của admin
  @GetMapping("/page/Admin/TrangChuQuanLy")
  public String adminPage(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("userRole", auth.getAuthorities());  // Thêm vai trò vào Model
    return "page/Admin/TrangChuQuanLy";  // Trang dành cho Admin
  }

  // Trang chính của customer
  @GetMapping("/page/Customer/Home")
  public String customerPage(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("userRole", auth.getAuthorities());  // Thêm vai trò vào Model
    return "page/Customer/Home";  // Trang dành cho Customer
  }
}
