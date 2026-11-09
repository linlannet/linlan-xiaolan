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
import net.linlan.xiaolan.my.dto.WishInfoDto;
import net.linlan.xiaolan.my.entity.WishInfo;
import net.linlan.xiaolan.my.param.WishInfoParam;
import net.linlan.xiaolan.my.service.WishInfoService;

/**
*
* 心愿目标池读写控制类
* @author Linlan
* CreateTime 2025-11-17 16:59:49
* @version 1.0
* @since 1.0
*
*/
@RestController
@RequestMapping("/api/my")
public class WishInfoController extends BaseController {
    @Resource
    private WishInfoService wishInfoService;

    /** 获取心愿目标池列表或分页, 返回WishInfo对象.
     * @param param 参数查询对象
     * @return 列表的心愿目标池数据 {@link List} 对象，包含 {@link WishInfo} 列表:null,error;page,success
     */
    @PlatLog(value = "查询心愿目标池分页", category = 0)
    @GetMapping("/wishinfo/list")
    @Encrypt
    public ResponseEntity<List<WishInfo>> list(WishInfoParam param) {
        FrameUserDetails loginUser = SecurityUtils.getLoginUser();
        if (loginUser != null) {
            param.setMemberId(loginUser.getUserLid());
        }
        Page<WishInfoDto> result = wishInfoService.getPageDto(param);
        if (result == null) {
            return empty();
        }
        return successPage(result.getResult(), result.getPageSize(), result.getPageNum(),
            result.getTotal());
    }

    /**
     * 主键获取心愿目标池详细信息.
     * @param id 心愿目标主键ID|1
     * @return {@link WishInfo}:空null,error;分页page,success
     */
    @PlatLog(value = "主键获取心愿目标池详细信息", category = 1)
    @GetMapping(value = "/wishinfo/{id}")
    @Encrypt
    public ResponseEntity<WishInfo> findById(@PathVariable("id") Long id) {
        return success(wishInfoService.findById(id));
    }

    /** 新增心愿目标池数据操作逻辑，根据操作类型，执行新增操作.
     * @param input 对象信息，输入WishInfo对象
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "新增心愿目标池", category = 10)
    @PostMapping("/wishinfo/save")
    @Encrypt
    @LimitScope(name = "wishInfoSave", key = "wishInfoSave")
    public ResponseEntity<String> save(@RequestBody WishInfo input) {
        wishInfoService.save(input);
        return success();
    }

    /** 修改心愿目标池数据操作逻辑，根据操作类型，执行修改操作.
     * @param input 对象信息，输入WishInfo对象
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "修改心愿目标池", category = 20)
    @PostMapping("/wishinfo/update")
    @Encrypt
    @LimitScope(name = "wishInfoUpdate", key = "wishInfoUpdate")
    public ResponseEntity<String> update(@RequestBody WishInfo input) {
        wishInfoService.update(input);
        return success();
    }

    /** 删除心愿目标池数据操作逻辑，根据操作类型，执行删除操作.
     * @param ids 心愿目标主键ID:null;1;1,2,3|1
     * @return {@link String}:操作成功0;操作失败1
     */
    @PlatLog(value = "删除心愿目标池", category = 40)
    @PostMapping("/wishinfo/delete/{ids}")
    @Encrypt
    @LimitScope(name = "wishInfoDelete", key = "wishInfoDelete")
    public ResponseEntity<String> delete(@PathVariable Long[] ids) {
        wishInfoService.deleteByIds(ids);
        return success();
    }

}
