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
import net.linlan.xiaolan.my.dto.MindRecordDto;
import net.linlan.xiaolan.my.entity.MindRecord;
import net.linlan.xiaolan.my.param.MindRecordParam;
import net.linlan.xiaolan.my.service.MindRecordService;

/**
*
* 心愿提醒任务记录读写控制类
* @author Linlan
* CreateTime 2025-11-17 16:59:49
* @version 1.0
* @since 1.0
*
*/
@RestController
@RequestMapping("/api/my")
public class MindRecordController extends BaseController {
    @Resource
    private MindRecordService mindRecordService;

    /** 获取心愿提醒任务记录列表或分页, 返回MindRecord对象.
     * @param param 参数查询对象
     * @return 列表的心愿提醒任务记录数据 {@link List} 对象，包含 {@link MindRecord} 列表:null,error;page,success
     */
    @PlatLog(value = "查询心愿提醒任务记录分页", category = 0)
    @GetMapping("/mindrecord/list")
    @Encrypt
    public ResponseEntity<List<MindRecord>> list(MindRecordParam param) {
        FrameUserDetails loginUser = SecurityUtils.getLoginUser();
        if (loginUser != null) {
            param.setMemberId(loginUser.getUserLid());
        }
        Page<MindRecordDto> result = mindRecordService.getPageDto(param);
        if (result == null) {
            return empty();
        }
        return successPage(result.getResult(), result.getPageSize(), result.getPageNum(),
            result.getTotal());
    }

    /**
     * 主键获取心愿提醒任务记录详细信息.
     * @param id 提醒主键ID|1
     * @return {@link MindRecord}:空null,error;分页page,success
     */
    @PlatLog(value = "主键获取心愿提醒任务记录详细信息", category = 1)
    @GetMapping(value = "/mindrecord/{id}")
    @Encrypt
    public ResponseEntity<MindRecord> findById(@PathVariable("id") Long id) {
        return success(mindRecordService.findById(id));
    }

    /** 新增心愿提醒任务记录数据操作逻辑，根据操作类型，执行新增操作.
     * @param input 对象信息，输入MindRecord对象
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "新增心愿提醒任务记录", category = 10)
    @PostMapping("/mindrecord/save")
    @Encrypt
    @LimitScope(name = "mindRecordSave", key = "mindRecordSave")
    public ResponseEntity<String> save(@RequestBody MindRecord input) {
        mindRecordService.save(input);
        return success();
    }

    /** 修改心愿提醒任务记录数据操作逻辑，根据操作类型，执行修改操作.
     * @param input 对象信息，输入MindRecord对象
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "修改心愿提醒任务记录", category = 20)
    @PostMapping("/mindrecord/update")
    @Encrypt
    @LimitScope(name = "mindRecordUpdate", key = "mindRecordUpdate")
    public ResponseEntity<String> update(@RequestBody MindRecord input) {
        mindRecordService.update(input);
        return success();
    }

    /** 删除心愿提醒任务记录数据操作逻辑，根据操作类型，执行删除操作.
     * @param ids 提醒主键ID:null;1;1,2,3|1
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "删除心愿提醒任务记录", category = 40)
    @PostMapping("/mindrecord/delete/{ids}")
    @Encrypt
    @LimitScope(name = "mindRecordDelete", key = "mindRecordDelete")
    public ResponseEntity<String> delete(@PathVariable Long[] ids) {
        mindRecordService.deleteByIds(ids);
        return success();
    }

}
