package com.chengm.supplier.service;

import com.chengm.supplier.entity.Supplier;

import java.util.List;

public interface SupplierService {

    List<Supplier> list(String keyword);

    Supplier getById(Long id);

    void add(Supplier supplier);

    void update(Supplier supplier);

    void delete(Long id);
}
