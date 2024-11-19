package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Voucher;
import com.shoestore.Server.repositories.VoucherRepository;
import com.shoestore.Server.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/vouchers")
public class VoucherController {

    @Autowired
    private VoucherRepository voucherRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getVouchers(
            @RequestParam(required = false, defaultValue = "all") String status,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        Page<Voucher> vouchersPage;

        if ("all".equals(status)) {
            vouchersPage = voucherRepository.findByNameContainingIgnoreCase(search, paging);
        } else {
            vouchersPage = voucherRepository.findByStatusAndNameContainingIgnoreCase(status, search, paging);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("vouchers", vouchersPage.getContent());
        response.put("totalItems", vouchersPage.getTotalElements());
        response.put("totalPages", vouchersPage.getTotalPages());

        return ResponseEntity.ok(response);
    }
}

