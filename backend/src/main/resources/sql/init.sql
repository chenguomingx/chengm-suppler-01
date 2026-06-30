CREATE DATABASE IF NOT EXISTS chengm_supplier DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE chengm_supplier;

DROP TABLE IF EXISTS supplier;

CREATE TABLE supplier (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    supplier_name VARCHAR(200) NOT NULL COMMENT '公司名称',
    unified_social_credit_code VARCHAR(50) DEFAULT NULL COMMENT '统一社会信用代码',
    legal_representative VARCHAR(50) DEFAULT NULL COMMENT '法定代表人',
    registered_capital DECIMAL(18,2) DEFAULT NULL COMMENT '注册资本(万元)',
    establishment_date DATE DEFAULT NULL COMMENT '成立日期',
    business_status VARCHAR(20) DEFAULT NULL COMMENT '经营状态',
    province VARCHAR(50) DEFAULT NULL COMMENT '所属省份',
    city VARCHAR(50) DEFAULT NULL COMMENT '所属城市',
    district VARCHAR(50) DEFAULT NULL COMMENT '所属区县',
    business_registration_number VARCHAR(50) DEFAULT NULL COMMENT '工商注册号',
    organization_code VARCHAR(20) DEFAULT NULL COMMENT '组织机构代码',
    taxpayer_identification_number VARCHAR(50) DEFAULT NULL COMMENT '纳税人识别号',
    registration_authority VARCHAR(100) DEFAULT NULL COMMENT '登记机关',
    approval_date DATE DEFAULT NULL COMMENT '核准日期',
    business_term_start DATE DEFAULT NULL COMMENT '营业期限开始',
    business_term_end DATE DEFAULT NULL COMMENT '营业期限结束',
    enterprise_type VARCHAR(50) DEFAULT NULL COMMENT '企业类型',
    industry VARCHAR(100) DEFAULT NULL COMMENT '所属行业',
    business_scope TEXT DEFAULT NULL COMMENT '经营范围',
    former_name VARCHAR(200) DEFAULT NULL COMMENT '曾用名',
    insurance_count INT DEFAULT NULL COMMENT '参保人数',
    contact_person VARCHAR(50) DEFAULT NULL COMMENT '联系人',
    contact_phone VARCHAR(30) DEFAULT NULL COMMENT '联系电话',
    contact_email VARCHAR(100) DEFAULT NULL COMMENT '联系邮箱',
    address VARCHAR(255) DEFAULT NULL COMMENT '地址',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',
    status TINYINT DEFAULT 1 COMMENT '状态：1-启用，0-禁用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_supplier_name (supplier_name),
    UNIQUE KEY uk_unified_social_credit_code (unified_social_credit_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商表';

INSERT INTO supplier (supplier_name, unified_social_credit_code, legal_representative, registered_capital, establishment_date, business_status, province, city, district, business_registration_number, organization_code, taxpayer_identification_number, registration_authority, approval_date, business_term_start, business_term_end, enterprise_type, industry, business_scope, former_name, insurance_count, contact_person, contact_phone, contact_email, address, remark, status) VALUES
('华为技术有限公司', '914403001922038216', '赵明路', 4427061.06, '1987-09-15', '存续', '广东省', '深圳市', '龙岗区', '440301103090991', '19220382-1', '914403001922038216', '深圳市市场监督管理局', '2023-03-27', '1987-09-15', '2037-09-14', '有限责任公司（法人独资）', '计算机、通信和其他电子设备制造业', '信息与通信技术解决方案、智能终端、云计算与AI技术研发', NULL, 207532, '张经理', '13800138001', 'contact@huawei.com', '深圳市龙岗区坂田街道华为基地', '全球领先的ICT基础设施和智能终端提供商', 1),
('阿里巴巴（中国）有限公司', '91330100799655058B', '张勇', 15406.62, '2007-03-26', '存续', '浙江省', '杭州市', '余杭区', '330100400001129', '79965505-8', '91330100799655058B', '杭州市市场监督管理局', '2022-08-15', '2007-03-26', '2037-03-25', '有限责任公司（台港澳法人独资）', '互联网和相关服务', '技术开发、技术服务、技术咨询、成果转让、计算机软硬件、网络技术产品', '浙江淘宝网络有限公司', 123456, '李总监', '13800138002', 'contact@alibaba.com', '杭州市余杭区文一西路969号', '全球知名的电子商务和云计算公司', 1),
('腾讯科技（深圳）有限公司', '914403007708803860', '马化腾', 200000.00, '2000-02-23', '存续', '广东省', '深圳市', '南山区', '440301503280051', '77088038-6', '914403007708803860', '深圳市市场监督管理局', '2021-12-20', '2000-02-23', '2030-02-22', '有限责任公司（台港澳法人独资）', '互联网和相关服务', '从事计算机软硬件的技术开发、销售自行开发的软件、计算机技术服务及信息服务', NULL, 85678, '王主管', '13800138003', 'contact@tencent.com', '深圳市南山区科技园科技中一路腾讯大厦', '中国最大的互联网综合服务提供商之一', 1),
('北京字节跳动科技有限公司', '91110108MA00241X2L', '张一鸣', 1000.00, '2012-03-09', '存续', '北京市', '北京市', '海淀区', '110108014708866', 'MA00241X-2', '91110108MA00241X2L', '北京市海淀区市场监督管理局', '2023-01-18', '2012-03-09', '2032-03-08', '有限责任公司（自然人投资或控股）', '互联网和相关服务', '技术开发、技术推广、技术转让、技术咨询、技术服务、计算机系统服务', NULL, 67890, '赵经理', '13800138004', 'contact@bytedance.com', '北京市海淀区知春路甲48号', '全球知名的短视频和信息平台运营商', 1),
('小米科技有限责任公司', '91110108551385082Q', '雷军', 180000.00, '2010-03-03', '存续', '北京市', '北京市', '海淀区', '110108012660422', '55138508-2', '91110108551385082Q', '北京市海淀区市场监督管理局', '2022-09-15', '2010-03-03', '2030-03-02', '有限责任公司（自然人投资或控股）', '计算机、通信和其他电子设备制造业', '技术开发、货物进出口、技术进出口、代理进出口、销售通讯设备', '北京小米科技有限责任公司', 45678, '钱经理', '13800138005', 'contact@xiaomi.com', '北京市海淀区清河中街68号', '知名的智能手机和智能家居产品制造商', 1),
('宁德时代新能源科技股份有限公司', '91350900587527232L', '曾毓群', 244068.03, '2011-12-16', '存续', '福建省', '宁德市', '蕉城区', '350900100011287', '58752723-2', '91350900587527232L', '宁德市市场监督管理局', '2023-04-12', '2011-12-16', NULL, '股份有限公司（上市、自然人投资或控股）', '电气机械和器材制造业', '锂离子电池、锂聚合物电池、燃料电池、动力电池及相关电池材料的研发、生产和销售', NULL, 23456, '孙工程师', '13800138006', 'contact@catl.com', '宁德市蕉城区漳湾镇新港路2号', '全球领先的动力电池企业', 1),
('比亚迪股份有限公司', '91440300192317458F', '王传福', 291114.28, '1995-02-10', '存续', '广东省', '深圳市', '坪山区', '440301103032184', '19231745-8', '91440300192317458F', '深圳市市场监督管理局', '2023-02-28', '1995-02-10', NULL, '股份有限公司（上市）', '汽车制造业', '锂离子电池、太阳能电站、储能电站、电动车、电动大巴车及相关零部件的生产和销售', NULL, 34567, '周经理', '13800138007', 'contact@byd.com', '深圳市坪山区比亚迪路3009号', '新能源汽车和电池制造龙头企业', 1),
('美的集团股份有限公司', '91440600722473344C', '方洪波', 702172.34, '2000-04-07', '存续', '广东省', '佛山市', '顺德区', '440600000002816', '72247334-4', '91440600722473344C', '佛山市市场监督管理局', '2023-03-15', '2000-04-07', NULL, '股份有限公司（上市、自然人投资或控股）', '电气机械和器材制造业', '家用电器、电机、通讯设备及其零配件的生产制造', '佛山市美的集团有限公司', 18765, '吴主管', '13800138008', 'contact@midea.com', '佛山市顺德区北滘镇美的大道6号', '全球知名的家电制造企业', 1),
('京东集团股份有限公司', '91110000MA001FB17B', '刘强东', 138560.00, '2006-11-06', '存续', '北京市', '北京市', '亦庄经济技术开发区', '110000450007246', 'MA001FB1-7', '91110000MA001FB17B', '北京经济技术开发区市场监督管理局', '2022-11-20', '2006-11-06', '2026-11-05', '有限责任公司（台港澳法人独资）', '互联网和相关服务', '电子商务平台运营、物流配送、技术服务', NULL, 15678, '郑经理', '13800138009', 'contact@jd.com', '北京市亦庄经济技术开发区科创十一街18号', '中国领先的电商和物流企业', 0),
('美团集团有限公司', '91110105MA017QYL7P', '王兴', 100000.00, '2010-03-04', '存续', '北京市', '北京市', '朝阳区', '110105012646850', 'MA017QYL-7', '91110105MA017QYL7P', '北京市朝阳区市场监督管理局', '2023-01-25', '2010-03-04', '2030-03-03', '有限责任公司（自然人投资或控股）', '互联网和相关服务', '技术服务、技术开发、电子商务平台运营、餐饮配送服务', NULL, 12345, '冯经理', '13800138010', 'contact@meituan.com', '北京市朝阳区望京西街6号', '中国领先的生活服务电子商务平台', 1);
