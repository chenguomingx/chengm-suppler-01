package com.chengm.supplier.controller;

import com.chengm.supplier.common.Result;
import com.chengm.supplier.entity.Supplier;
import com.chengm.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public Result<List<Supplier>> list(@RequestParam(required = false) String keyword) {
        return Result.success(supplierService.list(keyword));
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
}
