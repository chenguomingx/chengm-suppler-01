package com.chengm.supplier.service;

import com.chengm.supplier.common.PageResult;
import com.chengm.supplier.entity.Supplier;

public interface SupplierService {

    PageResult<Supplier> page(String keyword, long pageNum, long pageSize);

    Supplier getById(Long id);

    void add(Supplier supplier);

    void update(Supplier supplier);

    void delete(Long id);

    void updateStatus(Long id, Integer status);
}
