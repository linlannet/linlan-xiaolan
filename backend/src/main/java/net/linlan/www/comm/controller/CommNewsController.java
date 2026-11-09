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
import net.linlan.www.comm.entity.CommNews;
import net.linlan.www.comm.service.CommNewsService;

/**
 * 新闻资讯控制器
 * 
 * @author Linlan
 * @version 1.0
 */
@RestController
@RequestMapping("/api/open/news")
public class CommNewsController extends BaseController {

    @Resource
    private CommNewsService commNewsService;

    /**
     * 获取新闻列表
     */
    @PlatLog(value = "获取新闻列表", category = 0)
    @GetMapping("/list")
    @Encrypt
    public ResponseEntity<List<CommNews>> list() {
        List<CommNews> list = commNewsService.getList(null);
        return success(list);
    }

    /**
     * 根据ID获取新闻详情
     */
    @PlatLog(value = "获取新闻详情", category = 1)
    @GetMapping("/{id}")
    @Encrypt
    public ResponseEntity<CommNews> findById(@PathVariable("id") Long id) {
        return success(commNewsService.findById(id));
    }

    /**
     * 保存新闻
     */
    @PlatLog(value = "保存新闻", category = 2)
    @PostMapping
    @Encrypt
    public ResponseEntity<Void> save(@RequestBody CommNews news) {
        commNewsService.save(news);
        return success();
    }

    /**
     * 更新新闻
     */
    @PlatLog(value = "更新新闻", category = 3)
    @PutMapping
    @Encrypt
    public ResponseEntity<Void> update(@RequestBody CommNews news) {
        commNewsService.update(news);
        return success();
    }

    /**
     * 删除新闻
     */
    @PlatLog(value = "删除新闻", category = 4)
    @DeleteMapping("/{id}")
    @Encrypt
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        commNewsService.deleteById(id);
        return success();
    }
}
