package com.shoestore.client.security;

import com.shoestore.client.dto.request.UserDTO;
import com.shoestore.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService; // Sử dụng UserService để truy xuất thông tin người dùng

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println(email);
		UserDTO user = userService.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}

		// Tạo đối tượng UserDetails từ thông tin người dùng
		return new org.springframework.security.core.userdetails.User(
						user.getEmail(),
						user.getPassword(),
						Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName())) // Gán quyền cho user
		);
	}
}
