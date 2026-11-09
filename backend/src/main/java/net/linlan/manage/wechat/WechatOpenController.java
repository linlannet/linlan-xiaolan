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

import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import net.linlan.annotation.OriginalResponse;
import net.linlan.commons.core.StringUtils;

/**
 * 微信开放平台相关控制器
 * 
 * @author Linlan
 */
@Slf4j
@RestController
@RequestMapping("/api/open/wechat")
public class WechatOpenController {

    private final WxMpService       wxMpService;
    private final WxMpMessageRouter messageRouter;

    // 使用构造器注入替代 @Resource
    public WechatOpenController(WxMpService wxMpService, WxMpMessageRouter messageRouter) {
        this.wxMpService = wxMpService;
        this.messageRouter = messageRouter;
    }

    /**
     * 微信公众号token验证
     * @param signature 签名
     * @param timestamp 时间
     * @param nonce 有效期
     * @param echostr 响应字符串
     * @return token验证
     */
    @GetMapping
    @OriginalResponse
    public String checkToken(@RequestParam(name = "signature", required = false) String signature,
                             @RequestParam(name = "timestamp", required = false) String timestamp,
                             @RequestParam(name = "nonce", required = false) String nonce,
                             @RequestParam(name = "echostr", required = false) String echostr) {

        log.info("微信Token验证请求 - signature: {}, timestamp: {}, nonce: {}, echostr: {}", signature,
            timestamp, nonce, echostr);

        // 参数完整性检查
        if (StringUtils.isAnyEmpty(signature, timestamp, nonce, echostr)) {
            log.error("微信Token验证失败：缺少必要参数 - signature:{}, timestamp:{}, nonce:{}, echostr:{}",
                signature, timestamp, nonce, echostr);
            return "FAIL";
        }

        try {
            boolean isValid = wxMpService.checkSignature(timestamp, nonce, signature);
            log.info("微信签名验证结果：{}", isValid ? "成功" : "失败");
            return isValid ? echostr : "FAIL";
        } catch (Exception e) {
            log.error("微信签名验证异常：", e);
            return "FAIL";
        }
    }

    /**
     * 微信公众号消息对接
     * @param requestBody 请求结构体
     * @param signature 签名
     * @param timestamp 时间
     * @param nonce 有效期
     * @param encType 加密类型
     * @param msgSignature 消息签名
     * @return 检查签名结果
     */
    @PostMapping(produces = "application/xml; charset=UTF-8")
    @OriginalResponse
    public String handleMessage(@RequestBody String requestBody,
                                @RequestParam("signature") String signature,
                                @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce,
                                @RequestParam(name = "encrypt_type", required = false) String encType,
                                @RequestParam(name = "msg_signature", required = false) String msgSignature) {
        log.info("\n接收微信请求：[[signature=[{}], encType=[{}], msgSignature=[{}],"
                 + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
            signature, encType, msgSignature, timestamp, nonce, requestBody);
        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }
        String out = null;
        if (encType == null) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage.toXml();
        } else if ("aes".equalsIgnoreCase(encType)) {
            // aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody,
                wxMpService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
            log.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if (outMessage == null) {
                return "";
            }
            out = outMessage.toEncryptedXml(wxMpService.getWxMpConfigStorage());
        }

        log.debug("\n组装回复信息：{}", out);
        return out;
    }

    /**
     * 微信消息路由
     *
     * @param message   消息
     * @return  路由输出
     */
    private WxMpXmlOutMessage route(WxMpXmlMessage message) {
        try {
            return this.messageRouter.route(message);
        } catch (Exception e) {
            log.error("路由消息时出现异常！", e);
        }

        return null;
    }
}
