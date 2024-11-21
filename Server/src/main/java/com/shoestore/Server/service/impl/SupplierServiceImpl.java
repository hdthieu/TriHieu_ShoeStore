package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.Supplier;
import com.shoestore.Server.repositories.SupplierRepository;
import com.shoestore.Server.service.SupplierService;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier getSupplier(int id) {
        return supplierRepository.findBySupplierID(id);
    }
}
