package com.shoestore.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AccountController {

  @GetMapping("/customer/account")
  public String showAccount() {
    return "/page/Customer/Account";
  }
  // Đảm bảo rằng URL khớp với yêu cầu của bạn
  @GetMapping("/customer/account/my-orders")
  public String showMyOrders() {
    // Trả về trang MyOrders.html trong thư mục templates
    return "page/Customer/MyOrders";  // Chỉ cần cung cấp đường dẫn từ thư mục templates mà không cần phần mở rộng .html
  }

  @GetMapping("/customer/account/my-wishlist")
  public String showMyWishList() {
    return "page/Customer/MyWishList";
  }

  @GetMapping("/customer/account/my-account")
  public String showMyAccount() {
    return "page/Customer/MyAccount";
  }

  @GetMapping("/customer/account/my-address")
  public String showAddress() {
    return "page/Customer/Address";
  }
}
