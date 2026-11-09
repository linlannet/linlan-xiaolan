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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 平台使用的数据库内加密字段全局配置
 * @author Linlan
 * CreateTime 2019/03/01 19:20
 */
@Data
@Component
public class PlatformSecurityConfig {

    /**
     * 前后端交互是否加密
     */
    public static boolean encrypt;
    /**
     * 公钥，提供给前端
     */
    public static String  publicKey;
    /**
     * 私钥
     */
    public static String  privateKey;

    @Value("${platform.backend.encrypt}")
    public void setEncrypt(boolean encrypt) {
        PlatformSecurityConfig.encrypt = encrypt;
    }

    @Value("${platform.backend.rsa.private_key}")
    public void setPrivateKey(String privateKey) {
        PlatformSecurityConfig.privateKey = privateKey;
    }

    @Value("${platform.backend.rsa.public_key}")
    public void setPublicKey(String publicKey) {
        PlatformSecurityConfig.publicKey = publicKey;
    }

    public static String JASYPT_KEY;

    @Value("${jasypt.encryptor.password}")
    public void setJasyptKey(String jasyptKey) {
        JASYPT_KEY = jasyptKey;
    }

    /**
     * 数据库敏感信息是否加密
     */
    public static String dbEncrypt;

    @Value("${platform.database.db_encrypt}")
    public void setDbEncrypt(String dbEncrypt) {
        PlatformSecurityConfig.dbEncrypt = dbEncrypt;
    }

    /**
     * database数据库敏感字段加密值
     */
    public static String dbKey;

    @Value("${platform.database.db_key}")
    public void setDbKey(String dbKey) {
        PlatformSecurityConfig.dbKey = dbKey;
    }

    /**
     * database数据库敏感字段对外提供加密值
     */
    public static String dbKeyOut;

    @Value("${platform.database.db_key_out}")
    public void setDbKeyOut(String dbKeyOut) {
        PlatformSecurityConfig.dbKeyOut = dbKeyOut;
    }

}
