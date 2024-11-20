package com.shoestore.Server.service;

import com.shoestore.Server.entities.Voucher;

import java.util.List;

public interface VoucherService{
    public Voucher addVoucher(Voucher voucher);
    public void deleteVoucher(int voucherID);
    public Voucher updateVoucher(int id, Voucher voucher);
}
