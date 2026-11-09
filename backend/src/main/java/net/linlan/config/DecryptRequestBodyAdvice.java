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

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

import lombok.extern.slf4j.Slf4j;

import net.linlan.annotation.Encrypt;
import net.linlan.annotation.NoSqlFilter;
import net.linlan.annotation.NoXssFilter;
import net.linlan.commons.db.filter.SqlUtils;
import net.linlan.utils.crypt.AESUtils;
import net.linlan.utils.crypt.RSAUtil;
import net.linlan.utils.exception.SupportException;
import net.linlan.utils.xss.XSSFilterUtils;

@Slf4j
//@RestControllerAdvice
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

    private static final String AES_KEY = "AES-KEY";
    private static final String AES_IV  = "AES-IV";

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter,
                                           Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        HttpHeaders headers = inputMessage.getHeaders();
        try {
            //这个request其实就是入参 可以从这里获取流
            //入参放在HttpInputMessage里面  这个方法的返回值也是HttpInputMessage
            InputStream inputStream = inputMessage.getBody();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }

        // 请求数据解密
        boolean decode = false;
        if (parameter.getMethod().isAnnotationPresent(Encrypt.class)) {
            //获取注解配置的包含和去除字段
            Encrypt serializedField = parameter.getMethodAnnotation(Encrypt.class);
            //出参是否需要加密
            decode = serializedField.decode();
        }
        if (decode && PlatformSecurityConfig.encrypt) {
            try {
                if (StrUtil.isEmpty(stringBuilder.toString())) {
                    return new MyHttpInputMessage(inputMessage.getHeaders(),
                        new ByteArrayInputStream(stringBuilder.toString().getBytes("UTF-8")));
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
                String aesKey = RSAUtil.decrypt(PlatformSecurityConfig.privateKey,
                    RSAUtil.getPrivateKey(encryptAesKey));
                String aesIv = RSAUtil.decrypt(PlatformSecurityConfig.privateKey,
                    RSAUtil.getPrivateKey(encryptAesIv));
                String encryptRequestData = stringBuilder.toString();
                String requestData = AESUtils.decrypt(encryptRequestData, aesKey, aesIv);
                if (!parameter.getMethod().isAnnotationPresent(NoXssFilter.class)) {
                    checkXssInvalidRequest(requestData);
                }
                if (!parameter.getMethod().isAnnotationPresent(NoSqlFilter.class)) {
                    checkSqlInvalidRequest(requestData);
                }
                //把数据放到我们封装的对象中
                return new MyHttpInputMessage(inputMessage.getHeaders(),
                    new ByteArrayInputStream(requestData.getBytes("UTF-8")));
            } catch (BadRequestException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                throw new RuntimeException("服务正在维护，请稍后再试");
            }
        }
        if (!parameter.getMethod().isAnnotationPresent(NoXssFilter.class)) {
            checkXssInvalidRequest(stringBuilder.toString());
        }
        if (!parameter.getMethod().isAnnotationPresent(NoSqlFilter.class)) {
            checkSqlInvalidRequest(stringBuilder.toString());
        }
        return new MyHttpInputMessage(inputMessage.getHeaders(),
            new ByteArrayInputStream(stringBuilder.toString().getBytes("UTF-8")));
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
                                MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage,
                                  MethodParameter parameter, Type targetType,
                                  Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    /**
     * 校验非法攻击字符
     *
     * @param requestData
     */
    private void checkXssInvalidRequest(String requestData) {
        if (XSSFilterUtils.hasXss(requestData)) {
            throw new SupportException("含有非法攻击字符,已禁止继续访问", HttpStatus.FORBIDDEN);
        }
    }

    /**
     * 校验非法攻击字符
     *
     * @param requestData
     */
    private void checkSqlInvalidRequest(String requestData) {
        if (SqlUtils.illegalSql(requestData)) {
            throw new SupportException("含有非法攻击字符,已禁止继续访问", HttpStatus.FORBIDDEN);
        }
    }

    //这里实现了HttpInputMessage 封装一个自己的HttpInputMessage
    class MyHttpInputMessage implements HttpInputMessage {
        HttpHeaders headers;
        InputStream body;

        public MyHttpInputMessage(HttpHeaders headers, InputStream body) {
            this.headers = headers;
            this.body = body;
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }

}
