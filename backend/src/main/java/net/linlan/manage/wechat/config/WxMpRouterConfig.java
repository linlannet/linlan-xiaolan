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
package net.linlan.manage.wechat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import net.linlan.manage.wechat.handler.LogHandler;
import net.linlan.manage.wechat.handler.MessageHandler;
import net.linlan.manage.wechat.handler.SubscribeHandler;
import net.linlan.manage.wechat.handler.UnSubscribeHandler;
import static me.chanjar.weixin.common.api.WxConsts.EventType.SUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.EventType.UNSUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType.EVENT;

@Configuration
@AllArgsConstructor
public class WxMpRouterConfig {
    /**
     * 用户关注行为
     */
    private final SubscribeHandler   subscribeHandler;
    /**
     * 用户取消关注行为
     */
    private final UnSubscribeHandler unSubscribeHandler;
    /**
     * 用户发送消息行为
     */
    private final MessageHandler     messageHandler;
    /**
     * 记录用户发送行为
     */
    private final LogHandler         logHandler;

    @Bean
    public WxMpMessageRouter wxMpMessageRouter(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);
        // 记录所有行为日志 异步执行
        newRouter.rule().handler(logHandler).async(true).next();

        // 用户关注
        newRouter.rule().async(false).msgType(EVENT).event(SUBSCRIBE).handler(subscribeHandler)
            .end();

        // 用户取消关注
        newRouter.rule().async(false).msgType(EVENT).event(UNSUBSCRIBE).handler(unSubscribeHandler)
            .end();

        // 默认
        newRouter.rule().async(false).handler(messageHandler).end();
        return newRouter;
    }
}
