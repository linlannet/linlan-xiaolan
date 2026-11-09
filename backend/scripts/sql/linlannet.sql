-- ============================================================
-- 麟览科技数据库初始化脚本
-- 创建时间: 2026-03-10
-- ============================================================

-- ============================================================
-- 解决方案/产品方案表
-- ============================================================
DROP TABLE IF EXISTS `COMM_SOLUTION`;
CREATE TABLE `COMM_SOLUTION` (
    `ID` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `TYPE` INT NOT NULL DEFAULT 1 COMMENT '类型: 1-平台运营 2-政企通用产品 3-行业解决方案',
    `TITLE` VARCHAR(255) NOT NULL COMMENT '标题',
    `DESCRIPTION` TEXT COMMENT '描述',
    `ICON` VARCHAR(500) COMMENT '图标',
    `SORT` INT DEFAULT 0 COMMENT '排序',
    `STATUS` INT DEFAULT 1 COMMENT '状态: 0-禁用 1-启用',
    `DEL_FLAG` INT DEFAULT 0 COMMENT '删除: 0-否 1-是',
    `CREATE_TIME` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `UPDATE_TIME` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`ID`),
    INDEX `IDX_TYPE` (`TYPE`),
    INDEX `IDX_STATUS` (`STATUS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='解决方案';

-- ============================================================
-- 服务表
-- ============================================================
DROP TABLE IF EXISTS `COMM_SERVICE`;
CREATE TABLE `COMM_SERVICE` (
    `ID` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `NAME` VARCHAR(255) NOT NULL COMMENT '服务名称',
    `DESCRIPTION` TEXT COMMENT '服务描述',
    `ICON` VARCHAR(500) COMMENT '图标',
    `CATEGORY` VARCHAR(100) COMMENT '分类',
    `SORT` INT DEFAULT 0 COMMENT '排序',
    `STATUS` INT DEFAULT 1 COMMENT '状态: 0-禁用 1-启用',
    `DEL_FLAG` INT DEFAULT 0 COMMENT '删除: 0-否 1-是',
    `CREATE_TIME` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `UPDATE_TIME` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`ID`),
    INDEX `IDX_STATUS` (`STATUS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务';

-- ============================================================
-- 新闻资讯表
-- ============================================================
DROP TABLE IF EXISTS `COMM_NEWS`;
CREATE TABLE `COMM_NEWS` (
    `ID` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `TITLE` VARCHAR(500) NOT NULL COMMENT '标题',
    `SUMMARY` VARCHAR(1000) COMMENT '摘要',
    `CONTENT` LONGTEXT COMMENT '内容',
    `COVER_IMAGE` VARCHAR(500) COMMENT '封面图',
    `PUBLISH_DATE` DATETIME COMMENT '发布日期',
    `SORT` INT DEFAULT 0 COMMENT '排序',
    `STATUS` INT DEFAULT 1 COMMENT '状态: 0-禁用 1-启用',
    `DEL_FLAG` INT DEFAULT 0 COMMENT '删除: 0-否 1-是',
    `CREATE_TIME` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `UPDATE_TIME` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`ID`),
    INDEX `IDX_STATUS` (`STATUS`),
    INDEX `IDX_PUBLISH_DATE` (`PUBLISH_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='新闻资讯';

-- ============================================================
-- 联系我们/留言表
-- ============================================================
DROP TABLE IF EXISTS `COMM_CONTACT`;
CREATE TABLE `COMM_CONTACT` (
    `ID` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `NAME` VARCHAR(100) NOT NULL COMMENT '姓名',
    `PHONE` VARCHAR(20) COMMENT '电话',
    `EMAIL` VARCHAR(100) COMMENT '邮箱',
    `COMPANY` VARCHAR(200) COMMENT '公司',
    `CONTENT` TEXT NOT NULL COMMENT '留言内容',
    `STATUS` INT DEFAULT 0 COMMENT '处理状态: 0-未处理 1-已处理',
    `REMARK` VARCHAR(500) COMMENT '备注',
    `DEL_FLAG` INT DEFAULT 0 COMMENT '删除: 0-否 1-是',
    `CREATE_TIME` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `UPDATE_TIME` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`ID`),
    INDEX `IDX_STATUS` (`STATUS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='联系我们';
