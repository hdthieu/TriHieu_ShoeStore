package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Voucher;
import com.shoestore.Server.repositories.VoucherRepository;
import com.shoestore.Server.service.VoucherService;
import org.apache.coyote.Response;
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
import java.util.Optional;

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

    // Phương thức GET để lấy thông  tin voucher theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Voucher> getVoucherById(@PathVariable int id) {
        Optional<Voucher> voucher = voucherRepository.findById(id);
        if (voucher.isPresent()) {
            return ResponseEntity.ok(voucher.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Trả về lỗi 404 nếu không tìm thấy voucher
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voucher> updateVoucher(@PathVariable int id, @RequestBody Voucher voucher) {
        Voucher updatedVoucher = voucherService.updateVoucher(id, voucher);
        if (updatedVoucher != null) {
            return ResponseEntity.ok(updatedVoucher);  // Trả về voucher đã được cập nhật
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Nếu không tìm thấy voucher
        }
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
