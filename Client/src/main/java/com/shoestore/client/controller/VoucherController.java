package com.shoestore.client.controller;
import com.shoestore.client.dto.request.VoucherDTO;
import com.shoestore.client.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/vouchers")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @GetMapping("/view")
    public String showVoucherForm(Model model) {
        // Gọi API server qua service và lấy danh sách VoucherDTO
        List<VoucherDTO> vouchers = voucherService.getVouchersFromServer();

        // Đưa danh sách VoucherDTO vào model
        model.addAttribute("vouchers", vouchers);

        // Trả về view ThemMoiKhuyenMai
        return "page/Admin/ThemMoiKhuyenMai";
    }
}

