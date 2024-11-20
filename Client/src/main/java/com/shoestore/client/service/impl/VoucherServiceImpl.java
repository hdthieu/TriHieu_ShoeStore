package com.shoestore.client.service.impl;

import com.shoestore.client.dto.request.VoucherDTO;
import com.shoestore.client.dto.response.VoucherResponseDTO;
import com.shoestore.client.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private RestTemplate restTemplate;

    public List<VoucherDTO> getVouchersFromServer() {
        String apiUrl = "http://localhost:8080/vouchers";    // URL API trả về đối tượng chứa mảng "vouchers"
        // Sử dụng ResponseEntity để lấy đối tượng JSON chứa danh sách vouchers
        ResponseEntity<VoucherResponseDTO> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null,
                VoucherResponseDTO.class
        );

        // Truyền trả về danh sách vouchers từ đối tượng JSON
        return response.getBody().getVouchers();
    }

}
