package com.shoestore.Server.service;

import com.shoestore.Server.entities.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier getSupplier(int id);
    public List<Supplier> getAllSupplier();
}
