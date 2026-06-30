package com.chengm.supplier.controller;

import com.chengm.supplier.common.PageResult;
import com.chengm.supplier.common.Result;
import com.chengm.supplier.entity.Supplier;
import com.chengm.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/page")
    public Result<PageResult<Supplier>> page(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize) {
        return Result.success(supplierService.page(keyword, pageNum, pageSize));
    }

    @GetMapping("/{id}")
    public Result<Supplier> getById(@PathVariable Long id) {
        return Result.success(supplierService.getById(id));
    }

    @PostMapping
    public Result<Void> add(@Validated @RequestBody Supplier supplier) {
        supplierService.add(supplier);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@Validated @RequestBody Supplier supplier) {
        supplierService.update(supplier);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return Result.success();
    }

    @PutMapping("/{id}/status")
    public Result<Void> updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        supplierService.updateStatus(id, status);
        return Result.success();
    }
}
