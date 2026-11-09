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
package net.linlan.xiaolan.my.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.Page;

import jakarta.annotation.Resource;
import net.linlan.annotation.Encrypt;
import net.linlan.annotation.LimitScope;
import net.linlan.commons.core.annotation.PlatLog;
import net.linlan.commons.core.http.ResponseEntity;
import net.linlan.frame.FrameUserDetails;
import net.linlan.frame.api.BaseController;
import net.linlan.frame.web.SecurityUtils;
import net.linlan.xiaolan.my.dto.CheckRecordDto;
import net.linlan.xiaolan.my.entity.CheckRecord;
import net.linlan.xiaolan.my.param.CheckRecordParam;
import net.linlan.xiaolan.my.service.CheckRecordService;

/**
*
* 记账打卡记录读写控制类
* @author Linlan
* CreateTime 2025-11-17 16:59:49
* @version 1.0
* @since 1.0
*
*/
@RestController
@RequestMapping("/api/my")
public class CheckRecordController extends BaseController {
    @Resource
    private CheckRecordService checkRecordService;

    /** 获取记账打卡记录列表或分页, 返回CheckRecord对象.
     * @param param 参数查询对象
     * @return 列表的记账打卡记录数据 {@link List} 对象，包含 {@link CheckRecord} 列表:null,error;page,success
     */
    @PlatLog(value = "查询记账打卡记录分页", category = 0)
    @GetMapping("/checkrecord/list")
    @Encrypt
    public ResponseEntity<List<CheckRecord>> list(CheckRecordParam param) {
        FrameUserDetails loginUser = SecurityUtils.getLoginUser();
        if (loginUser != null) {
            param.setMemberId(loginUser.getUserLid());
        }
        Page<CheckRecordDto> result = checkRecordService.getPageDto(param);
        if (result == null) {
            return empty();
        }
        return successPage(result.getResult(), result.getPageSize(), result.getPageNum(),
            result.getTotal());
    }

    /**
     * 主键获取记账打卡记录详细信息.
     * @param id 记账打卡主键ID|1
     * @return {@link CheckRecord}:空null,error;分页page,success
     */
    @PlatLog(value = "主键获取记账打卡记录详细信息", category = 1)
    @GetMapping(value = "/checkrecord/{id}")
    @Encrypt
    public ResponseEntity<CheckRecord> findById(@PathVariable("id") Long id) {
        return success(checkRecordService.findById(id));
    }

    /** 新增记账打卡记录数据操作逻辑，根据操作类型，执行新增操作.
     * @param input 对象信息，输入CheckRecord对象
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "新增记账打卡记录", category = 10)
    @PostMapping("/checkrecord/save")
    @Encrypt
    @LimitScope(name = "checkRecordSave", key = "checkRecordSave")
    public ResponseEntity<String> save(@RequestBody CheckRecord input) {
        checkRecordService.save(input);
        return success();
    }

    /** 修改记账打卡记录数据操作逻辑，根据操作类型，执行修改操作.
     * @param input 对象信息，输入CheckRecord对象
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "修改记账打卡记录", category = 20)
    @PostMapping("/checkrecord/update")
    @Encrypt
    @LimitScope(name = "checkRecordUpdate", key = "checkRecordUpdate")
    public ResponseEntity<String> update(@RequestBody CheckRecord input) {
        checkRecordService.update(input);
        return success();
    }

    /** 删除记账打卡记录数据操作逻辑，根据操作类型，执行删除操作.
     * @param ids 记账打卡主键ID:null;1;1,2,3|1
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "删除记账打卡记录", category = 40)
    @PostMapping("/checkrecord/delete/{ids}")
    @Encrypt
    @LimitScope(name = "checkRecordDelete", key = "checkRecordDelete")
    public ResponseEntity<String> delete(@PathVariable Long[] ids) {
        checkRecordService.deleteByIds(ids);
        return success();
    }

}
