package com.chengm.supplier.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "供应商名称不能为空")
    private String supplierName;

    private String contactPerson;

    private String contactPhone;

    private String contactEmail;

    private String address;

    private String remark;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
