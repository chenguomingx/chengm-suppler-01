package com.chengm.supplier.service.impl;

import com.chengm.supplier.common.PageResult;
import com.chengm.supplier.entity.Supplier;
import com.chengm.supplier.mapper.SupplierMapper;
import com.chengm.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public PageResult<Supplier> page(String keyword, long pageNum, long pageSize) {
        String trimmedKeyword = StringUtils.hasText(keyword) ? keyword.trim() : null;
        long offset = (pageNum - 1) * pageSize;
        List<Supplier> records = supplierMapper.selectPage(trimmedKeyword, offset, pageSize);
        long total = supplierMapper.selectCount(trimmedKeyword);
        return new PageResult<>(records, total, pageNum, pageSize);
    }

    @Override
    public Supplier getById(Long id) {
        return supplierMapper.selectById(id);
    }

    @Override
    public void add(Supplier supplier) {
        Supplier nameExist = supplierMapper.selectByName(supplier.getSupplierName());
        if (nameExist != null) {
            throw new RuntimeException("供应商名称已存在");
        }
        if (StringUtils.hasText(supplier.getUnifiedSocialCreditCode())) {
            Supplier codeExist = supplierMapper.selectByUnifiedSocialCreditCode(supplier.getUnifiedSocialCreditCode());
            if (codeExist != null) {
                throw new RuntimeException("统一社会信用代码已存在");
            }
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
        String newCode = supplier.getUnifiedSocialCreditCode();
        String oldCode = exist.getUnifiedSocialCreditCode();
        boolean codeChanged = (newCode == null && oldCode != null)
                || (newCode != null && !newCode.equals(oldCode));
        if (codeChanged && StringUtils.hasText(newCode)) {
            Supplier codeExist = supplierMapper.selectByUnifiedSocialCreditCode(newCode);
            if (codeExist != null) {
                throw new RuntimeException("统一社会信用代码已存在");
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

    @Override
    public void updateStatus(Long id, Integer status) {
        Supplier exist = supplierMapper.selectById(id);
        if (exist == null) {
            throw new RuntimeException("供应商不存在");
        }
        supplierMapper.updateStatus(id, status);
        redisTemplate.delete(SUPPLIER_CACHE_KEY);
    }
}
