package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.Role;
import com.shoestore.Server.entities.User;
import com.shoestore.Server.repositories.RoleRepository;
import com.shoestore.Server.repositories.UserRepository;
import com.shoestore.Server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        Role role = roleRepository.findByName("Customer"); // Mặc định là "Customer"
        if (user.getRole() != null && "Admin".equals(user.getRole().getName())) {
            // Nếu người dùng yêu cầu là Admin, thì tìm vai trò "Admin"
            role = roleRepository.findByName("Admin");
            if (role == null) {
                role = new Role();
                role.setName("Admin");
                roleRepository.save(role);
            }
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Mã hóa mật khẩu
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getUserByName(String username) {
        return List.of();
    }
}
