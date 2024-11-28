package com.shoestore.Server.controller;

import com.shoestore.Server.entities.User;
import com.shoestore.Server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {
  @Autowired
  private UserService userService;
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
    User user = userService.findByEmail(email);
    if (user != null && password.equals(user.getPassword())) {
      return ResponseEntity.ok(user);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Invalid email or password");
  }
  // Đăng ký người dùng mới
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody User user) {
    // Kiểm tra nếu email đã tồn tại trong hệ thống
    if (userService.findByEmail(user.getEmail()) != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body("Email already exists"); // Trả về thông báo nếu email đã tồn tại
    }
    userService.save(user); // Lưu người dùng mới vào cơ sở dữ liệu
    return ResponseEntity.status(HttpStatus.CREATED)
            .body("User registered successfully"); // Trả về thông báo thành công
  }

  // Tìm người dùng theo email
  @GetMapping("/findByEmail")
  public ResponseEntity<?> findByEmail(@RequestParam String email) {
    User user = userService.findByEmail(email);
    if (user != null) {
      return ResponseEntity.ok(user); // Trả về thông tin người dùng nếu tìm thấy
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("User not found"); // Trả về thông báo lỗi nếu không tìm thấy người dùng
  }

  // Lấy danh sách tất cả người dùng
  @GetMapping("/findAll")
  public ResponseEntity<?> findAllUsers() {
    return ResponseEntity.ok(userService.findAll()); // Trả về danh sách tất cả người dùng
  }
}
