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
package net.linlan.config;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

import lombok.extern.slf4j.Slf4j;

import net.linlan.annotation.Encrypt;
import net.linlan.annotation.OriginalResponse;
import net.linlan.commons.core.StringUtils;
import net.linlan.commons.core.http.ResponseEntity;
import net.linlan.utils.crypt.AESUtils;
import net.linlan.utils.crypt.RSAUtil;
import net.linlan.utils.exception.FrameApiError;

@Slf4j
//@RestControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice {

    private static final String AES_KEY = "AES-KEY";
    private static final String AES_IV  = "AES-IV";

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 原始响应 不做任何封装加密处理
        if (returnType.getMethod().isAnnotationPresent(OriginalResponse.class)) {
            return body;
        }
        // 监控响应 不做任何封装加密处理
        if (StringUtils.isNotBlank(request.getURI().getPath())
            && request.getURI().getPath().indexOf("actuator/prometheus") > -1) {
            return body;
        }
        boolean encode = false;
        HttpHeaders headers = request.getHeaders();
        if (returnType.getMethod().isAnnotationPresent(Encrypt.class)) {
            //获取注解配置的包含和去除字段
            Encrypt serializedField = returnType.getMethodAnnotation(Encrypt.class);
            //出参是否需要加密
            encode = serializedField.encode();
        }
        // 加密
        if (encode && PlatformSecurityConfig.encrypt) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String result = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(body);

                if (StrUtil.isEmpty(result)) {
                    return result;
                }
                // 获取请求头部AESkey和AESIv
                List<String> keys = headers.get(AES_KEY);
                List<String> ivs = headers.get(AES_IV);
                if (CollectionUtil.isEmpty(keys) && CollectionUtil.isEmpty(ivs)) {
                    keys = headers.get(AES_KEY.toLowerCase());
                    ivs = headers.get(AES_IV.toLowerCase());
                }
                String encryptAesKey = keys.get(0);
                String encryptAesIv = ivs.get(0);

                // 使用RSA公钥解密
                String aesKey = RSAUtil.decrypt(encryptAesKey,
                    RSAUtil.getPrivateKey(PlatformSecurityConfig.privateKey));
                String aesIv = RSAUtil.decrypt(encryptAesIv,
                    RSAUtil.getPrivateKey(PlatformSecurityConfig.privateKey));

                return buildResult(AESUtils.encrypt(result, aesKey, aesIv), returnType,
                    selectedConverterType, request, response);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                throw new RuntimeException("服务正在维护，请稍后再试");
            }
        }
        return buildResult(body, returnType, selectedConverterType, request, response);
    }

    // 处理非Restful风格的响应数据
    private ResponseEntity buildResult(Object originBody, MethodParameter returnType,
                                       Class selectedConverterType, ServerHttpRequest request,
                                       ServerHttpResponse response) {
        // 封装相应参数  数据status设置对应status   response status设置200
        response.setStatusCode(HttpStatus.OK);

        if (originBody instanceof FrameApiError) {
            FrameApiError error = (FrameApiError) originBody;
            return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), error.getMessage());
        }
        if (originBody instanceof LinkedHashMap) {
            LinkedHashMap bodyMap = (LinkedHashMap) originBody;
            if (bodyMap.containsKey("status") && bodyMap.containsKey("message")) {
                return new ResponseEntity(Integer.parseInt(bodyMap.get("status").toString()),
                    bodyMap.get("message").toString());
            }
        }
        return ResponseEntity.ok(originBody);
    }
}
