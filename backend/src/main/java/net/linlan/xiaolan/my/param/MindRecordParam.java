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
 * MindRecord数据域:心愿提醒任务记录查询参数实体类
 * 
 * @author Linlan
 * CreateTime 2025-11-17 16:59:49
 * @version 1.0
 * @since 1.0
 *
 */
@Data
public class MindRecordParam extends BaseParam {

    /**
     * 提醒主键ID
     */
    private Long    id;
    /**
     * 任务ID
     */
    private String  scheduleId;
    /**
     * 心愿目标主键ID
     */
    private Long    wishId;
    /**
     * 会员主键ID
     */
    private Long    memberId;
    /**
     * 创建者显示名称
     */
    private String  viewName;
    /**
     * 第三方身份类型，wechat_mini
     */
    private String  accountType;
    /**
     * 第三方OpenId
     */
    private String  accountKey;
    /**
     * 消息模板
     */
    private String  messageTplId;
    /**
     * 消息标题
     */
    private String  title;
    /**
     * 消息内容
     */
    private String  content;
    /**
     * 任务状态:0未启动1正常2暂停
     */
    private Integer status;
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
