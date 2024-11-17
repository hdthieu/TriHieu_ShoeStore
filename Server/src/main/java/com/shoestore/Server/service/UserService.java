package com.shoestore.Server.service;

import com.shoestore.Server.entities.User;

import java.util.List;

public interface UserService {
    List<User> getUserByName(String username);
}
