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
package net.linlan.www.comm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import net.linlan.annotation.Encrypt;
import net.linlan.commons.core.annotation.PlatLog;
import net.linlan.commons.core.http.ResponseEntity;
import net.linlan.frame.api.BaseController;
import net.linlan.www.comm.entity.CommContact;
import net.linlan.www.comm.service.CommContactService;

/**
 * 联系我们控制器
 * 
 * @author Linlan
 * @version 1.0
 */
@RestController
@RequestMapping("/api/open/contact")
public class CommContactController extends BaseController {

    @Resource
    private CommContactService commContactService;

    /**
     * 获取联系我们列表
     */
    @PlatLog(value = "获取联系我们列表", category = 0)
    @GetMapping("/list")
    @Encrypt
    public ResponseEntity<List<CommContact>> list() {
        List<CommContact> list = commContactService.getList(null);
        return success(list);
    }

    /**
     * 根据ID获取联系我们详情
     */
    @PlatLog(value = "获取联系我们详情", category = 1)
    @GetMapping("/{id}")
    @Encrypt
    public ResponseEntity<CommContact> findById(@PathVariable("id") Long id) {
        return success(commContactService.findById(id));
    }

    /**
     * 提交联系我们
     */
    @PlatLog(value = "提交联系我们", category = 2)
    @PostMapping
    @Encrypt
    public ResponseEntity<Void> save(@RequestBody CommContact contact) {
        commContactService.save(contact);
        return success();
    }

    /**
     * 更新联系我们
     */
    @PlatLog(value = "更新联系我们", category = 3)
    @PutMapping
    @Encrypt
    public ResponseEntity<Void> update(@RequestBody CommContact contact) {
        commContactService.update(contact);
        return success();
    }

    /**
     * 删除联系我们
     */
    @PlatLog(value = "删除联系我们", category = 4)
    @DeleteMapping("/{id}")
    @Encrypt
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        commContactService.deleteById(id);
        return success();
    }
}
