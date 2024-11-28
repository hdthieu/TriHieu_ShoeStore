package com.shoestore.client.service.impl;

import com.shoestore.client.dto.request.UserDTO;
import com.shoestore.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String SERVER_BASE_URL = "http://localhost:8080/";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<UserDTO> authenticateUser(UserDTO userDTO) {
        // Gửi yêu cầu POST đến server để xác thực người dùng
        String apiUrl = SERVER_BASE_URL + "auth/login?email=" + userDTO.getEmail() + "&password=" + userDTO.getPassword();
        return restTemplate.postForEntity(apiUrl, null, UserDTO.class);
    }

    @Override
    public void save(UserDTO userDTO) {
        // Lưu người dùng mới (trong trường hợp đăng ký)
        String apiUrl = SERVER_BASE_URL + "auth/register";
        restTemplate.postForEntity(apiUrl, userDTO, Void.class);
    }

//    @Override
//    public UserDTO findByEmail(String email) {
//        String url = SERVER_BASE_URL + "auth/findByEmail?email=" + email;
//        System.out.println(url);
//        System.out.println(restTemplate.getForObject(url, UserDTO.class));
//        return restTemplate.getForObject(url, UserDTO.class);
//    }

    @Override
    public UserDTO findByEmail(String email) {
        // In ra URL request để kiểm tra
        System.out.println("Request URL: " + "http://localhost:8080/auth/findByEmail?email=" + email);

        // Tạo URL với tham số email từ đầu vào
        String url = "http://localhost:8080/auth/findByEmail?email=" + email;

        // Gửi yêu cầu GET đến API và nhận kết quả dưới dạng UserDTO
        ResponseEntity<UserDTO> response = restTemplate.exchange(
                url, HttpMethod.GET, null, UserDTO.class
        );

        // In kết quả trả về từ API
        System.out.println("Response from API: " + response.getBody());

        // Trả về đối tượng UserDTO nhận được từ API
        return response.getBody();
    }


    @Override
    public List<UserDTO> findAll() {
        String url = SERVER_BASE_URL + "findAll";
        UserDTO[] userArray = restTemplate.getForObject(url, UserDTO[].class);
        return List.of(userArray);
    }

    @Override
    public List<UserDTO> getUserByName(String username) {
        String url = SERVER_BASE_URL + "getUserByName?username=" + username;
        UserDTO[] userArray = restTemplate.getForObject(url, UserDTO[].class);
        return List.of(userArray);
    }
}
