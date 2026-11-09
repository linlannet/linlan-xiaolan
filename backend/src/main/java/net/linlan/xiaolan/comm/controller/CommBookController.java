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
package net.linlan.xiaolan.comm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.Page;

import jakarta.annotation.Resource;
import net.linlan.annotation.Encrypt;
import net.linlan.annotation.LimitScope;
import net.linlan.commons.core.*;
import net.linlan.commons.core.annotation.PlatLog;
import net.linlan.commons.core.http.ResponseEntity;
import net.linlan.frame.api.BaseController;
import net.linlan.xiaolan.comm.dto.CommBookDto;
import net.linlan.xiaolan.comm.entity.CommBook;
import net.linlan.xiaolan.comm.param.CommBookParam;
import net.linlan.xiaolan.comm.service.CommBookService;

/**
*
* 公共记账本读写控制类
* @author Linlan
* CreateTime 2025-11-17 16:57:19
* @version 1.0
* @since 1.0
*
*/
@RestController
@RequestMapping("/api/open")
public class CommBookController extends BaseController {
    @Resource
    private CommBookService commBookService;

    /** 获取公共记账本列表或分页, 返回CommBook对象.
     * @param param 参数查询对象
     * @return 列表的公共记账本数据 {@link List} 对象，包含 {@link CommBook} 列表:null,error;page,success
     */
    @PlatLog(value = "查询公共记账本分页", category = 0)
    @GetMapping("/book/list")
    @Encrypt
    public ResponseEntity<List<CommBook>> list(CommBookParam param) {
        Page<CommBookDto> result = commBookService.getPageDto(param);
        if (result == null) {
            return empty();
        }
        return successPage(result.getResult(), result.getPageSize(), result.getPageNum(),
            result.getTotal());
    }

    /**
     * 主键获取公共记账本详细信息.
     * @param id 记账本台账主键ID|1
     * @return {@link CommBook}:空null,error;分页page,success
     */
    @PlatLog(value = "主键获取公共记账本详细信息", category = 1)
    @GetMapping(value = "/book/{id}")
    @Encrypt
    public ResponseEntity<CommBook> findById(@PathVariable("id") Long id) {
        return success(commBookService.findById(id));
    }

    /** 新增公共记账本数据操作逻辑，根据操作类型，执行新增操作.
     * @param input 对象信息，输入CommBook对象
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "新增公共记账本", category = 10)
    @PostMapping("/book/save")
    @Encrypt
    @LimitScope(name = "commBookSave", key = "commBookSave")
    public ResponseEntity<String> save(@RequestBody CommBook input) {
        commBookService.save(input);
        return success();
    }

    /** 修改公共记账本数据操作逻辑，根据操作类型，执行修改操作.
     * @param input 对象信息，输入CommBook对象
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "修改公共记账本", category = 20)
    @PostMapping("/book/update")
    @Encrypt
    @LimitScope(name = "commBookUpdate", key = "commBookUpdate")
    public ResponseEntity<String> update(@RequestBody CommBook input) {
        commBookService.update(input);
        return success();
    }

    /** 删除公共记账本数据操作逻辑，根据操作类型，执行删除操作.
     * @param ids 记账本台账主键ID:null;1;1,2,3|1
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "删除公共记账本", category = 40)
    @PostMapping("/book/delete/{ids}")
    @Encrypt
    @LimitScope(name = "commBookDelete", key = "commBookDelete")
    public ResponseEntity<String> delete(@PathVariable Long[] ids) {
        commBookService.deleteByIds(ids);
        return success();
    }

}
