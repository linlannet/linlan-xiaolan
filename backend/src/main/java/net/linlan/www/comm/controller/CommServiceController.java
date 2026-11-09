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
import net.linlan.www.comm.entity.CommService;
import net.linlan.www.comm.service.CommServiceService;

/**
 * 服务控制器
 * 
 * @author Linlan
 * @version 1.0
 */
@RestController
@RequestMapping("/api/open/service")
public class CommServiceController extends BaseController {

    @Resource
    private CommServiceService commServiceService;

    /**
     * 获取服务列表
     */
    @PlatLog(value = "获取服务列表", category = 0)
    @GetMapping("/list")
    @Encrypt
    public ResponseEntity<List<CommService>> list() {
        List<CommService> list = commServiceService.getList(null);
        return success(list);
    }

    /**
     * 根据ID获取服务详情
     */
    @PlatLog(value = "获取服务详情", category = 1)
    @GetMapping("/{id}")
    @Encrypt
    public ResponseEntity<CommService> findById(@PathVariable("id") Long id) {
        return success(commServiceService.findById(id));
    }

    /**
     * 保存服务
     */
    @PlatLog(value = "保存服务", category = 2)
    @PostMapping
    @Encrypt
    public ResponseEntity<Void> save(@RequestBody CommService service) {
        commServiceService.save(service);
        return success();
    }

    /**
     * 更新服务
     */
    @PlatLog(value = "更新服务", category = 3)
    @PutMapping
    @Encrypt
    public ResponseEntity<Void> update(@RequestBody CommService service) {
        commServiceService.update(service);
        return success();
    }

    /**
     * 删除服务
     */
    @PlatLog(value = "删除服务", category = 4)
    @DeleteMapping("/{id}")
    @Encrypt
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        commServiceService.deleteById(id);
        return success();
    }
}
