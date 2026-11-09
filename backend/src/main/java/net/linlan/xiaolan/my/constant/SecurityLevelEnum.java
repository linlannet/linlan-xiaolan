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

import net.linlan.utils.enums.BaseEnumInteger;

/**
 * 安全级别枚举
 */
public enum SecurityLevelEnum implements BaseEnumInteger {

                                                          /**
                                                           * 1级
                                                           */
                                                          LEVEL1(1, "1级"),
                                                          /**
                                                           * 2级
                                                           */
                                                          LEVEL2(2, "2级"),
                                                          /**
                                                           * 3级
                                                           */
                                                          LEVEL3(3, "3级"),
                                                          /**
                                                           * 4级
                                                           */
                                                          LEVEL4(4, "4级"),
                                                          /**
                                                           * 5级
                                                           */
                                                          LEVEL5(5, "5级");

    /**
     * 配置项名称
     */
    private int    key;
    /**
     * 字典值
     */
    private String value;

    SecurityLevelEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
