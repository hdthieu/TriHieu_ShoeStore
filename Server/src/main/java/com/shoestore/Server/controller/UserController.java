package com.shoestore.Server.controller;

import com.shoestore.Server.dto.UserDTO;
import com.shoestore.Server.entities.User;
import com.shoestore.Server.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
  @Autowired
  private UserService userService;
  @Autowired
  private ModelMapper modelMapper;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
    User user = userService.findByEmail(email);
    if (user != null && password.equals(user.getPassword())) {
      return ResponseEntity.ok(user);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Invalid email or password");
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
    User user = modelMapper.map(userDTO, User.class);

    if (userService.findByEmail(user.getEmail()) != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body("Email already exists");
    }
    userService.save(user);

    return ResponseEntity.status(HttpStatus.CREATED)
            .body("User registered successfully");
  }

  // Tìm người dùng theo email
  @GetMapping("/findByEmail")
  public ResponseEntity<?> findByEmail(@RequestParam String email) {
    User user = userService.findByEmail(email);
    if (user != null) {
      return ResponseEntity.ok(user);

    }
    return ResponseEntity.ok().body(null);
  }

  // Lấy danh sách tất cả người dùng
  @GetMapping("/findAll")
  public ResponseEntity<?> findAllUsers() {
    return ResponseEntity.ok(userService.findAll()); // Trả về danh sách tất cả người dùng
  }
}
