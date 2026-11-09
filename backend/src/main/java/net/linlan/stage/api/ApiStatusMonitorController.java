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
package net.linlan.stage.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import net.linlan.annotation.AuthIgnore;
import net.linlan.commons.core.http.ResponseEntity;
import net.linlan.commons.script.json.StringMap;
import net.linlan.sys.web.KernelAdminUtils;
import net.linlan.utils.constant.Constants;
import net.linlan.utils.http.HttpContextUtils;

/**
 * 平台当前监控状态
 *
 * @author Linlan
 * CreateTime:12/19/17 9:00 PM
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("api/stage")
public class ApiStatusMonitorController {
    @Value("${spring.profiles.active}")
    private String activeMode;

    /** 每次发版前，将版本号进行调整，以明确当前发布的版本和启动的版本是一致的
     * @param params    查询参数
     * @return  查询结果
     */
    @AuthIgnore
    @GetMapping("getStatus")
    public ResponseEntity<Map<String, Object>> getStatus(@RequestParam Map<String, Object> params) {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String url = request.getRequestURL().toString();
        url = KernelAdminUtils.getDomainMore(url);
        Map<String, Object> map = new StringMap().put(Constants.FIELD_VERSION, "1.1.X")
            .put("activeMode", activeMode).put("url", url).put(Constants.REQUEST_PARAMS, params)
            .put("timestamp", System.currentTimeMillis()).map();
        return ResponseEntity.ok(map);
    }

}
