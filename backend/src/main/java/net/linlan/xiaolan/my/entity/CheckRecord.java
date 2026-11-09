/*
 * xiaolan-backend - 小览生活服务端
 * Copyright © 2020-2025 Linlan (open@linlan.net)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.linlan.xiaolan.my.entity;

import java.util.Date;

import lombok.Data;

import net.linlan.commons.core.RandomUtils;
import net.linlan.frame.FrameUserDetails;
import net.linlan.frame.web.SecurityUtils;
import net.linlan.utils.entity.BaseEntity;

/**
 *
 * CheckRecord数据域:记账打卡记录实体类
 * 
 * @author Linlan
 * CreateTime 2025-11-17 16:59:49
 * @version 1.0
 * @since 1.0
 *
 */
@Data
public class CheckRecord extends BaseEntity {

    /**
     * 记账打卡主键ID
     */
    private Long    id;

    /**
     * 分类主键ID
     */
    private Long    categoryId;

    /**
     * 分类名称
     */
    private String  categoryName;

    /**
     * 记账本主键ID
     */
    private Long    bookId;

    /**
     * 记账本台账名称
     */
    private String  bookName;

    /**
     * 心愿目标主键ID
     */
    private Long    wishId;

    /**
     * 心愿目标名称
     */
    private String  wishName;

    /**
     * 主键ID
     */
    private Long    memberId;

    /**
     * 创建者显示名称
     */
    private String  viewName;

    /**
     * 记账打卡类型，JIZHANG记账，MUBIAO目标
     */
    private String  type;

    /**
     * 记账打卡类型，ZHICHU支出，SHOURU收入，DAKA打卡
     */
    private String  bizType;

    /**
     * 记账打卡名称
     */
    private String  name;

    /**
     * 记账打卡代码
     */
    private String  code;

    /**
     * 记账打卡时间
     */
    private Date    checkTime;

    /**
     * 记账打卡金额
     */
    private Double  checkMoney;

    /**
     * 记账打卡配置JSON
     */
    private String  content;

    /**
     * 状态，0异常关闭1正常2升级3锁定
     */
    private String  status;

    /**
     * 排序
     */
    private Integer priority;

    /**
     * 删除标记，0正常1已删除2应用内受限3回收站
     */
    private Integer delFlag;

    /**
     * 描述
     */
    private String  description;

    /**
     * 备用1
     */
    private String  spare1;

    /**
     * 备用2
     */
    private String  spare2;

    /**
     * 初始化方法
     */
    public void init() {
        //添加对数据库或实体对象的默认值处理
        if (getId() == null) {
            setId(RandomUtils.randomLid());
        }
        FrameUserDetails loginUser = SecurityUtils.getLoginUser();
        if (loginUser != null) {
            setMemberId(loginUser.getUserLid());
            setViewName(loginUser.getViewName());
        }
    }

}
