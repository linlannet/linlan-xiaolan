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
package net.linlan.xiaolan.comm.entity;

import lombok.Data;

import net.linlan.commons.core.RandomUtils;
import net.linlan.utils.entity.BaseEntity;

/**
 *
 * CommCategory数据域:提醒分类信息实体类
 * 
 * @author Linlan
 * CreateTime 2025-11-17 16:57:19
 * @version 1.0
 * @since 1.0
 *
 */
@Data
public class CommCategory extends BaseEntity {

    /**
     * 分类主键ID
     */
    private Long    id;

    /**
     * 父分类ID
     */
    private Long    parentId;

    /**
     * 分类类型，TIXING提醒，ZIXUN咨询
     */
    private String  type;

    /**
     * 分类类型，ZHICHU支出，SHOURU收入
     */
    private String  bizType;

    /**
     * 分类名称
     */
    private String  name;

    /**
     * 分类代码
     */
    private String  code;

    /**
     * 分类配置JSON
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
     * 创建者显示名称
     */
    private String  creatorName;

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

    }

}
