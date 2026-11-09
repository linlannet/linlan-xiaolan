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

import java.util.Date;

import lombok.Data;

import net.linlan.utils.param.BaseParam;

/**
 *
 * WishInfo数据域:心愿目标池查询参数实体类
 * 
 * @author Linlan
 * CreateTime 2025-11-17 16:59:49
 * @version 1.0
 * @since 1.0
 *
 */
@Data
public class WishInfoParam extends BaseParam {

    /**
     * 心愿目标主键ID
     */
    private Long    id;
    /**
     * 应用ID
     */
    private String  appId;
    /**
     * 心愿目标模板ID
     */
    private String  templateId;
    /**
     * 主键ID
     */
    private Long    memberId;
    /**
     * 创建者显示名称
     */
    private String  viewName;
    /**
     * 绑定记账本ID
     */
    private Long    bookId;
    /**
     * 绑定记账本名称
     */
    private String  bookName;
    /**
     * 类型，可关联CATEGORY
     */
    private String  type;
    /**
     * 心愿目标名称
     */
    private String  name;
    /**
     * 心愿目标代码
     */
    private String  code;
    /**
     * 心愿级别，1,2,3,4,5
     */
    private String  wichLevel;
    /**
     * 开始时间，默认当前时间
     */
    private Date    beginTime;
    /**
     * 周期，RI日，ZHOU周，YUE月，JI季度
     */
    private String  cycle;
    /**
     * 周末是否例外，如果例外则不分摊每日金额，0否1是
     */
    private Boolean ifWeekendEx;
    /**
     * 期初金额
     */
    private Double  initMoney;
    /**
     * 目标金额
     */
    private Double  aimMoney;
    /**
     * 月结转移方式
     */
    private String  modeTrans;
    /**
     * 提醒配置
     */
    private String  mindContent;
    /**
     * 状态，SAVED暂存START启动PAUSE暂停FINISH已结束
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
    /**
     * 备用3
     */
    private String  spare3;
    /**
     * 备用4
     */
    private String  spare4;

}
