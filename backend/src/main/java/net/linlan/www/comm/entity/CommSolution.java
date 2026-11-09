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
package net.linlan.www.comm.entity;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import net.linlan.commons.core.RandomUtils;
import net.linlan.utils.entity.BaseEntity;

/**
 * 解决方案/产品方案实体类
 * 
 * @author Linlan
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommSolution extends BaseEntity {

    /**
     * 主键ID
     */
    private Long    id;

    /**
     * 方案类型: 1-平台运营 2-政企通用产品 3-行业解决方案
     */
    private Integer type;

    /**
     * 标题
     */
    private String  title;

    /**
     * 描述
     */
    private String  description;

    /**
     * 图标
     */
    private String  icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态: 0-禁用 1-启用
     */
    private Integer status;

    /**
     * 删除标记
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    private Date    createTime;

    /**
     * 更新时间
     */
    private Date    updateTime;

    /**
     * 初始化方法
     */
    public void init() {
        if (getId() == null) {
            setId(RandomUtils.randomLid());
        }
        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }
        if (getUpdateTime() == null) {
            setUpdateTime(new Date());
        }
    }
}
