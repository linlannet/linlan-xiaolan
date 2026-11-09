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
package net.linlan.www.comm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import net.linlan.commons.db.annotation.DataSource;
import net.linlan.frame.comm.datasource.BaseDataSourceNames;
import net.linlan.www.comm.dao.CommServiceDao;
import net.linlan.www.comm.entity.CommService;

/**
 * 服务服务类
 * 
 * @author Linlan
 * @version 1.0
 */
@Service
public class CommServiceService {

    @Resource
    private CommServiceDao dao;

    /**
     * 获取服务列表
     */
    @DataSource(name = BaseDataSourceNames.FIRST)
    public List<CommService> getList(Map<String, Object> map) {
        return dao.getList(map);
    }

    /**
     * 根据ID查询
     */
    @DataSource(name = BaseDataSourceNames.FIRST)
    public CommService findById(Long id) {
        return dao.findById(id);
    }

    /**
     * 保存
     */
    @DataSource(name = BaseDataSourceNames.FIRST)
    public void save(CommService service) {
        service.init();
        dao.save(service);
    }

    /**
     * 更新
     */
    @DataSource(name = BaseDataSourceNames.FIRST)
    public void update(CommService service) {
        dao.update(service);
    }

    /**
     * 删除
     */
    @DataSource(name = BaseDataSourceNames.FIRST)
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    /**
     * 批量删除
     */
    @DataSource(name = BaseDataSourceNames.FIRST)
    public void deleteByIds(Long[] ids) {
        if (ids != null && ids.length > 0) {
            dao.deleteByIds(ids);
        }
    }

    /**
     * 统计数量
     */
    @DataSource(name = BaseDataSourceNames.FIRST)
    public int getCount(Map<String, Object> map) {
        return dao.getCount(map);
    }
}
