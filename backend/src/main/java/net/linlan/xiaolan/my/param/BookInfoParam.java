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
package net.linlan.xiaolan.my.param;

import lombok.Data;

import net.linlan.utils.param.BaseParam;

/**
 *
 * BookInfo数据域:记账本台账查询参数实体类
 * 
 * @author Linlan
 * CreateTime 2025-11-17 16:59:49
 * @version 1.0
 * @since 1.0
 *
 */
@Data
public class BookInfoParam extends BaseParam {

    /**
     * 主键ID
     */
    private Long    id;
    /**
     * 会员主键ID
     */
    private Long    memberId;
    /**
     * 创建者显示名称
     */
    private String  viewName;
    /**
     * 记账本ID
     */
    private Long    bookId;
    /**
     * 记账本台账名称
     */
    private String  bookName;
    /**
     * 安全级别，1,2,3,4,5
     */
    private Integer securityLevel;
    /**
     * 是否纳入统计，0否1是
     */
    private Boolean ifStat;
    /**
     * 状态，1正常
     */
    private String  status;
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

}
