package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.User;
import com.shoestore.Server.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUserByName(String username) {
        return List.of();
    }
}
