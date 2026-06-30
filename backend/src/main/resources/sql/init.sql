CREATE DATABASE IF NOT EXISTS chengm_supplier DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE chengm_supplier;

DROP TABLE IF EXISTS supplier;

CREATE TABLE supplier (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    supplier_name VARCHAR(100) NOT NULL COMMENT '供应商名称',
    contact_person VARCHAR(50) DEFAULT NULL COMMENT '联系人',
    contact_phone VARCHAR(30) DEFAULT NULL COMMENT '联系电话',
    contact_email VARCHAR(100) DEFAULT NULL COMMENT '联系邮箱',
    address VARCHAR(255) DEFAULT NULL COMMENT '地址',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',
    status TINYINT DEFAULT 1 COMMENT '状态：1-启用，0-禁用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_supplier_name (supplier_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商表';

INSERT INTO supplier (supplier_name, contact_person, contact_phone, contact_email, address, remark) VALUES
('深圳科技有限公司', '张三', '13800138001', 'zhangsan@tech.com', '深圳市南山区科技园', '优质供应商'),
('广州贸易有限公司', '李四', '13800138002', 'lisi@trade.com', '广州市天河区珠江新城', '长期合作伙伴'),
('北京制造有限公司', '王五', '13800138003', 'wangwu@mfg.com', '北京市朝阳区亦庄', '主要原材料供应商');
