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
package net.linlan.manage.wechat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.Resource;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import net.linlan.annotation.Encrypt;
import net.linlan.commons.core.http.ResponseEntity;
import net.linlan.sys.web.RedisService;
import static net.linlan.utils.constant.CacheConstants.WX_MP_USER;

/**
 * 微信服务号公众号相关控制器
 * 
 * @author Linlan
 */
@Slf4j
@RestController
@RequestMapping("/api/open/wxmp")
public class WechatMpController {

    @Resource
    private RedisService            redisService;

    private final WxMpService       wxMpService;
    private final WxMpMessageRouter wxMpMessageRouter;

    // 使用构造器注入替代 @Resource
    public WechatMpController(WxMpService wxMpService, WxMpMessageRouter wxMpMessageRouter) {
        this.wxMpService = wxMpService;
        this.wxMpMessageRouter = wxMpMessageRouter;
    }

    private static final String OAUTH2_REDIRECT_URL = "https://linlannet/xiaolan/mp/home";

    /**
     * 微信公众号 获取oauth2完整授权地址
     *
     * @return 对象
     */
    @GetMapping("/getRedirect")
    @Encrypt
    public ResponseEntity<Object> getRedirect() {
        Map<String, Object> result = new HashMap<>();
        result.put("url", wxMpService.getOAuth2Service().buildAuthorizationUrl(OAUTH2_REDIRECT_URL,
            WxConsts.OAuth2Scope.SNSAPI_USERINFO, null));
        return ResponseEntity.ok(result);
    }

    /**
     * 微信公众号 oauth2授权后 获取用户信息
     * @param code  代码
     * @return 对象
     * @throws WxErrorException     异常
     */
    @GetMapping("/login")
    @Encrypt
    public ResponseEntity<Object> login(String code) throws WxErrorException {
        WxOAuth2AccessToken wxOAuth2AccessToken = wxMpService.getOAuth2Service()
            .getAccessToken(code);
        WxOAuth2UserInfo wxOAuth2UserInfo = wxMpService.getOAuth2Service()
            .getUserInfo(wxOAuth2AccessToken, null);
        Map<String, Object> result = new HashMap<>();
        // TODO: oauth获取用户信息后保存
        redisService.set(WX_MP_USER + "oauth:user:" + wxOAuth2UserInfo.getOpenid(),
            wxOAuth2UserInfo);
        result.put("user", wxOAuth2UserInfo);
        return ResponseEntity.ok(result);
    }

    /**
     * 微信公众号 页面端原生能力调用
     * @param url   路径URL
     * @return 对象
     */
    @GetMapping("/getJsapiTicket")
    @Encrypt
    public ResponseEntity<Object> getJsapiTicket(@RequestParam String url) {
        Map<String, Object> result = new HashMap<>();
        return ResponseEntity.ok(result);
    }
}
