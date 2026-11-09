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
import net.linlan.commons.core.annotation.PlatLog;
import net.linlan.commons.core.http.ResponseEntity;
import net.linlan.frame.api.BaseController;
import net.linlan.xiaolan.comm.dto.CommCategoryDto;
import net.linlan.xiaolan.comm.entity.CommCategory;
import net.linlan.xiaolan.comm.param.CommCategoryParam;
import net.linlan.xiaolan.comm.service.CommCategoryService;

/**
*
* 提醒分类信息读写控制类
* @author Linlan
* CreateTime 2025-11-17 16:57:19
* @version 1.0
* @since 1.0
*
*/
@RestController
@RequestMapping("/api/open")
public class CommCategoryController extends BaseController {
    @Resource
    private CommCategoryService commCategoryService;

    /** 获取提醒分类信息列表或分页, 返回CommCategory对象.
     * @param param 参数查询对象
     * @return 列表的提醒分类信息数据 {@link List} 对象，包含 {@link CommCategory} 列表:null,error;page,success
     */
    @PlatLog(value = "查询提醒分类信息分页", category = 0)
    @GetMapping("/category/list")
    @Encrypt
    public ResponseEntity<List<CommCategory>> list(CommCategoryParam param) {
        Page<CommCategoryDto> result = commCategoryService.getPageDto(param);
        if (result == null) {
            return empty();
        }
        return successPage(result.getResult(), result.getPageSize(), result.getPageNum(),
            result.getTotal());
    }

    /**
     * 主键获取提醒分类信息详细信息.
     * @param id 分类主键ID|1
     * @return {@link CommCategory}:空null,error;分页page,success
     */
    @PlatLog(value = "主键获取提醒分类信息详细信息", category = 1)
    @GetMapping(value = "/category/{id}")
    @Encrypt
    public ResponseEntity<CommCategory> findById(@PathVariable("id") Long id) {
        return success(commCategoryService.findById(id));
    }

    /** 新增提醒分类信息数据操作逻辑，根据操作类型，执行新增操作.
     * @param input 对象信息，输入CommCategory对象
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "新增提醒分类信息", category = 10)
    @PostMapping("/category/save")
    @Encrypt
    @LimitScope(name = "commCategorySave", key = "commCategorySave")
    public ResponseEntity<String> save(@RequestBody CommCategory input) {
        commCategoryService.save(input);
        return success();
    }

    /** 修改提醒分类信息数据操作逻辑，根据操作类型，执行修改操作.
     * @param input 对象信息，输入CommCategory对象
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "修改提醒分类信息", category = 20)
    @PostMapping("/category/update")
    @Encrypt
    @LimitScope(name = "commCategoryUpdate", key = "commCategoryUpdate")
    public ResponseEntity<String> update(@RequestBody CommCategory input) {
        commCategoryService.update(input);
        return success();
    }

    /** 删除提醒分类信息数据操作逻辑，根据操作类型，执行删除操作.
     * @param ids 分类主键ID:null;1;1,2,3|1
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "删除提醒分类信息", category = 40)
    @PostMapping("/category/delete/{ids}")
    @Encrypt
    @LimitScope(name = "commCategoryDelete", key = "commCategoryDelete")
    public ResponseEntity<String> delete(@PathVariable Long[] ids) {
        commCategoryService.deleteByIds(ids);
        return success();
    }

}
