package com.shoestore.Server.service;

import com.shoestore.Server.entities.User;

import java.util.List;

public interface UserService {
    List<User> getUserByName(String username);
    public void save(User user);

    public User findByEmail(String email);

    public List<User> findAll();
    User findById(int id);
}
