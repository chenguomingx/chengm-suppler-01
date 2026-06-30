package com.chengm.supplier.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "供应商名称不能为空")
    private String supplierName;

    private String unifiedSocialCreditCode;

    private String legalRepresentative;

    private BigDecimal registeredCapital;

    private LocalDate establishmentDate;

    private String businessStatus;

    private String province;

    private String city;

    private String district;

    private String businessRegistrationNumber;

    private String organizationCode;

    private String taxpayerIdentificationNumber;

    private String registrationAuthority;

    private LocalDate approvalDate;

    private LocalDate businessTermStart;

    private LocalDate businessTermEnd;

    private String enterpriseType;

    private String industry;

    private String businessScope;

    private String formerName;

    private Integer insuranceCount;

    private String contactPerson;

    private String contactPhone;

    private String contactEmail;

    private String address;

    private String remark;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
