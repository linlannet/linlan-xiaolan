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
package net.linlan.xiaolan.my.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import jakarta.annotation.Resource;
import net.linlan.commons.core.ObjectUtils;
import net.linlan.commons.db.annotation.DataSource;
import net.linlan.frame.comm.datasource.BaseDataSourceNames;
import net.linlan.xiaolan.my.dao.MindRecordDao;
import net.linlan.xiaolan.my.dto.MindRecordDto;
import net.linlan.xiaolan.my.entity.MindRecord;
import net.linlan.xiaolan.my.param.MindRecordParam;

/**
 *
 * MindRecord数据域:心愿提醒任务记录服务类
 * 
 * @author Linlan
 * CreateTime 2025-11-17 16:59:49
 * @version 1.0
 * @since 1.0
 *
 */
@Service
public class MindRecordService {

    @Resource
    private MindRecordDao dao;

    /** get the list of entity MindRecord
     * 列表方法，返回{@link MindRecord} 列表
     * @param map the input select conditions
     * @return 列表的心愿提醒任务记录数据 {@link List} 对象，包含 {@link MindRecord} 列表
     */
    //@DataFilter(tableAlias = "B", owner = false)
    @DataSource(name = BaseDataSourceNames.SECOND)
    public List<MindRecord> getList(Map<String, Object> map) {
        return dao.getList(map);
    }

    /** find the entity by input id, return entity
     * 对象详情方法，通过id查询对象{@link MindRecord}
     * @param id 提醒主键ID|1
     * @return {@link MindRecord}
     */
    @DataSource(name = BaseDataSourceNames.SECOND)
    public MindRecord findById(Long id) {
        return dao.findById(id);
    }

    /** save the entity with input object
     * 保存对象方法
     * @param mindRecord the input mindRecord
     */
    //@Transactional
    @DataSource(name = BaseDataSourceNames.SECOND)
    public void save(MindRecord mindRecord) {
        mindRecord.init();
        dao.save(mindRecord);
    }

    /** batch save the entity with input list
     * 保存对象心愿提醒任务记录，批量保存list
     * @param list {@link MindRecord}
     */
    @DataSource(name = BaseDataSourceNames.SECOND)
    public void batchSave(List<MindRecord> list) {
        dao.batchSave(list);
    }

    /** update the entity with input object
     * 更新对象方法
     * @param mindRecord the input mindRecord
     */
    //@Transactional
    @DataSource(name = BaseDataSourceNames.SECOND)
    public void update(MindRecord mindRecord) {
        dao.update(mindRecord);
    }

    /** delete the entity by input id
     * 删除方法，通过id删除对象
     * @param id 提醒主键ID|1
     */
    @DataSource(name = BaseDataSourceNames.SECOND)
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    /** batch delete the entity by input ids
     * 批量删除方法，通过ids删除对象
     * @param ids the input ids
     */
    //@Transactional
    @DataSource(name = BaseDataSourceNames.SECOND)
    public void deleteByIds(Long[] ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return;
        }
        dao.deleteByIds(ids);
    }

    /** query the total count by input select conditions
     * 通过输入的条件查询记录总数
     * @param map the input select conditions
     * @return total count
     */
    @DataSource(name = BaseDataSourceNames.SECOND)
    public int getCount(Map<String, Object> map) {
        return dao.getCount(map);
    }

    /** get the page of dto MindRecordDto
     * 列表分页方法，返回分页DTO对象列表
     * @param param the input param 查询参数
     * @return 分页的心愿提醒任务记录数据 {@link Page} 对象，包含 {@link MindRecord} 列表
     */
    //@DataFilter(tableAlias = "B", owner = false)
    @DataSource(name = BaseDataSourceNames.SECOND)
    public Page<MindRecordDto> getPageDto(MindRecordParam param) {
        PageHelper.startPage(param.getPage(), param.getLimit());
        return dao.getPageDto(param);
    }

    /** get the dto by input id, return dto
     * 对象详情方法，通过id查询对象{@link MindRecordDto}
     * @param id 提醒主键ID|1
     * @return {@link MindRecordDto}
     */
    @DataSource(name = BaseDataSourceNames.SECOND)
    public MindRecordDto getDtoById(Long id) {
        return dao.getDtoById(id);
    }

}
