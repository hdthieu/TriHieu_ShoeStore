package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.Voucher;
import com.shoestore.Server.repositories.VoucherRepository;
import com.shoestore.Server.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepository voucherRepository;

    public VoucherServiceImpl(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    public Voucher addVoucher(Voucher voucher) {
        Voucher entityVoucher = new Voucher();

        // Set các trường cơ bản
        entityVoucher.setName(voucher.getName());
        entityVoucher.setDescription(voucher.getDescription());
        entityVoucher.setStartDate(voucher.getStartDate());
        entityVoucher.setEndDate(voucher.getEndDate());
        entityVoucher.setDiscountType(voucher.getDiscountType());
        entityVoucher.setDiscountValue(voucher.getDiscountValue());
        entityVoucher.setGiaTriDonToiThieu(voucher.getGiaTriDonToiThieu());

        // Tính toán trạng thái (status) dựa trên ngày
        LocalDate today = LocalDate.now();
        if (voucher.getStartDate().isAfter(today)) {
            entityVoucher.setStatus("Upcoming");
        } else if (!voucher.getStartDate().isAfter(today) && !voucher.getEndDate().isBefore(today)) {
            entityVoucher.setStatus("Active");
        } else {
            entityVoucher.setStatus("Expired");
        }

        // Lưu vào cơ sở dữ liệu
        return voucherRepository.save(entityVoucher);
    }

    @Override
    public void deleteVoucher(int voucherID) {
        voucherRepository.deleteById(voucherID);  // Xóa khuyến mãi theo voucherID
    }

}
