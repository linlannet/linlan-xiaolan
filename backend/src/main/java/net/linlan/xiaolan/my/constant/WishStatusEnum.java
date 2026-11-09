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
package net.linlan.xiaolan.my.constant;

import net.linlan.utils.enums.BaseEnumString;

/**
 * 心愿状态枚举类
 */
public enum WishStatusEnum implements BaseEnumString {
                                                      /**
                                                       * 暂存
                                                       */
                                                      SAVED("SAVED", "暂存"),
                                                      /**
                                                       * 启动
                                                       */
                                                      START("START", "启动"),
                                                      /**
                                                       * 暂停
                                                       */
                                                      PAUSE("PAUSE", "暂停"),
                                                      /**
                                                       * 已结束
                                                       */
                                                      FINISH("FINISH", "已结束"),
                                                      /**
                                                       * 其他
                                                       */
                                                      QITA("QITA", "其他"),;

    /**
     * 配置项名称
     */
    private String key;
    /**
     * 配置项值
     */
    private String value;

    WishStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
