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
package net.linlan.xiaolan.comm.dao;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;

import net.linlan.commons.db.mybatis.MybatisBaseDao;
import net.linlan.xiaolan.comm.dto.CommBookDto;
import net.linlan.xiaolan.comm.entity.CommBook;
import net.linlan.xiaolan.comm.param.CommBookParam;

/**
*
* CommBook数据域:公共记账本持久层Dao
* 
* @author Linlan
* CreateTime 2025-11-17 16:57:19
* @version 1.0
* @since 1.0
*
*/
@Mapper
public interface CommBookDao extends MybatisBaseDao<CommBook> {

    /** get the page of dto CommBookDto
     * 列表分页方法，返回分页DTO对象列表
     * @param param the input param 查询参数
     * @return 分页的公共记账本数据 {@link Page} 对象，包含 {@link CommBook} 列表
     */
    Page<CommBookDto> getPageDto(CommBookParam param);

    /** get the dto by input id, return dto
     * 对象详情方法，通过id查询对象{@link CommBookDto}
     * @param id 记账本台账主键ID|1
     * @return {@link CommBookDto}
     */
    CommBookDto getDtoById(Long id);

}
