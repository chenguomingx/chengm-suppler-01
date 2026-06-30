package com.chengm.supplier.mapper;

import com.chengm.supplier.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierMapper {

    List<Supplier> selectList(@Param("keyword") String keyword);

    Supplier selectById(@Param("id") Long id);

    Supplier selectByName(@Param("supplierName") String supplierName);

    int insert(Supplier supplier);

    int update(Supplier supplier);

    int deleteById(@Param("id") Long id);
}
