package com.chengm.supplier.service.impl;

import com.chengm.supplier.entity.Supplier;
import com.chengm.supplier.mapper.SupplierMapper;
import com.chengm.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SupplierServiceImpl implements SupplierService {

    private static final String SUPPLIER_CACHE_KEY = "supplier:list";

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Supplier> list(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            Object cached = redisTemplate.opsForValue().get(SUPPLIER_CACHE_KEY);
            if (cached != null) {
                return (List<Supplier>) cached;
            }
            List<Supplier> list = supplierMapper.selectList(null);
            redisTemplate.opsForValue().set(SUPPLIER_CACHE_KEY, list, 10, TimeUnit.MINUTES);
            return list;
        }
        return supplierMapper.selectList(keyword.trim());
    }

    @Override
    public Supplier getById(Long id) {
        return supplierMapper.selectById(id);
    }

    @Override
    public void add(Supplier supplier) {
        Supplier exist = supplierMapper.selectByName(supplier.getSupplierName());
        if (exist != null) {
            throw new RuntimeException("供应商名称已存在");
        }
        if (supplier.getStatus() == null) {
            supplier.setStatus(1);
        }
        supplierMapper.insert(supplier);
        redisTemplate.delete(SUPPLIER_CACHE_KEY);
    }

    @Override
    public void update(Supplier supplier) {
        Supplier exist = supplierMapper.selectById(supplier.getId());
        if (exist == null) {
            throw new RuntimeException("供应商不存在");
        }
        if (!exist.getSupplierName().equals(supplier.getSupplierName())) {
            Supplier nameExist = supplierMapper.selectByName(supplier.getSupplierName());
            if (nameExist != null) {
                throw new RuntimeException("供应商名称已存在");
            }
        }
        supplierMapper.update(supplier);
        redisTemplate.delete(SUPPLIER_CACHE_KEY);
    }

    @Override
    public void delete(Long id) {
        supplierMapper.deleteById(id);
        redisTemplate.delete(SUPPLIER_CACHE_KEY);
    }
}
