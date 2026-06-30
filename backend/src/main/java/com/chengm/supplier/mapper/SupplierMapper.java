package com.chengm.supplier.mapper;

import com.chengm.supplier.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierMapper {

    List<Supplier> selectList(@Param("keyword") String keyword);

    List<Supplier> selectPage(@Param("keyword") String keyword, @Param("offset") long offset, @Param("pageSize") long pageSize);

    long selectCount(@Param("keyword") String keyword);

    Supplier selectById(@Param("id") Long id);

    Supplier selectByName(@Param("supplierName") String supplierName);

    Supplier selectByUnifiedSocialCreditCode(@Param("unifiedSocialCreditCode") String unifiedSocialCreditCode);

    int insert(Supplier supplier);

    int update(Supplier supplier);

    int deleteById(@Param("id") Long id);

    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
}
