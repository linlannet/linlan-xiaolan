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
package net.linlan.config;

import net.linlan.frame.comm.datasource.BaseDataSourceNames;

/**
 *
 * Filename:DataSourceNames.java
 * Desc:增加多数据源，在此配置
 * 本工程的数据源路径及配置，可以配置多种不同类型
 * 可以通过@DataSource(name = DataSourceNames.FIRST)，在Service内引用
 * 默认是FIRST数据源
 *
 * @author Linlan
 * CreateTime:2018/6/28 18:26
 *
 * @version 1.0
 * @since 1.0
 *
 */
public class DataSourceNames implements BaseDataSourceNames {

}
