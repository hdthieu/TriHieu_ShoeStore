package com.shoestore.client.service;

import com.shoestore.client.dto.request.VoucherDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoucherService {
    public List<VoucherDTO> getVouchersFromServer() ;
}
