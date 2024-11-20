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
@RequestMapping("/vouchers")
public class VoucherController {

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private VoucherService voucherService;

    @PostMapping("/add")
    public ResponseEntity<Voucher> addVoucher(@RequestBody Voucher voucherDTO) {
        Voucher savedVoucher = voucherService.addVoucher(voucherDTO);
        return ResponseEntity.ok(savedVoucher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVoucher(@PathVariable("id") int id) {
        voucherService.deleteVoucher(id);
        System.out.println("voucher deleted  : ");
        return ResponseEntity.ok("Voucher deleted");
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getVouchers(
            @RequestParam(required = false, defaultValue = "all") String status,
            @RequestParam(required = false, defaultValue = "") String search
    ) {
        // Lấy danh sách Voucher mà không phân trang
        List<Voucher> vouchers;

        // Kiểm tra trạng thái voucher và tìm kiếm theo tên
        if ("all".equals(status)) {
            vouchers = voucherRepository.findByNameContainingIgnoreCase(search);
        } else {
            vouchers = voucherRepository.findByStatusAndNameContainingIgnoreCase(status, search);
        }

        // Chuẩn bị response trả về cho client
        Map<String, Object> response = new HashMap<>();
        response.put("vouchers", vouchers);  // Danh sách voucher không phân trang

        return ResponseEntity.ok(response);
    }




}
