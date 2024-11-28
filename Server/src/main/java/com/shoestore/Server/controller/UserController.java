package com.shoestore.Server.controller;

import com.shoestore.Server.entities.User;
import com.shoestore.Server.repositories.UserRepository;
import com.shoestore.Server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private UserService userService;

//    @GetMapping("/role/{roleName}")
//    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String roleName) {
//        List<User> users = userService.getUsersByRole(roleName);
//        return ResponseEntity.ok(users);
//    }
}
