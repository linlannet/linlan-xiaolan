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
package net.linlan.stage.security;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 *
 * Filename:JasyptUtils.java
 * Desc:Jasypt安全框架加密类工具包
 * 此文件仅保留在本工程中，其他stage工程内不需要保留此文件
 * 此文件由运维发布人员统一配置和管理
 *
 * @author Linlan
 * CreateTime:9/23/21 9:14 PM
 *
 * @version 1.0
 * @since 1.0
 *
 */
public class JasyptUtils {

    /**
     * Jasypt生成加密结果
     *
     * @param password 配置文件中设定的加密密码 jasypt.encryptor.password
     * @param value    待加密值
     * @return    返回结果
     */
    public static String encryptPwd(String password, String value) {
        PooledPBEStringEncryptor encryptOr = new PooledPBEStringEncryptor();
        encryptOr.setConfig(cryptOr(password));
        String result = encryptOr.encrypt(value);
        return result;
    }

    /**
     * 解密
     *
     * @param password 配置文件中设定的加密密码 jasypt.encryptor.password
     * @param value    待解密密文
     * @return    返回结果
     */
    public static String decyptPwd(String password, String value) {
        PooledPBEStringEncryptor encryptOr = new PooledPBEStringEncryptor();
        encryptOr.setConfig(cryptOr(password));
        String result = encryptOr.decrypt(value);
        return result;
    }

    /**
     * @param password salt
     * @return    返回结果
     */
    public static SimpleStringPBEConfig cryptOr(String password) {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm(StandardPBEByteEncryptor.DEFAULT_ALGORITHM);
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName(null);
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        return config;
    }

    public static void main(String[] args) {

        System.out.println(encryptPwd("Ll2023!Backend_Prod",
            "jdbc:mysql://localhost:6306/basedb_1.0?serverTimezone=Asia/Shanghai&characterEncoding=UTF-8&&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true"));
        System.out.println(encryptPwd("Ll2023!Backend_Prod", "lldba"));
        System.out.println(encryptPwd("Ll2023!Backend_Prod", "Uyj1k?2025!_RS"));
        System.out.println(encryptPwd("Ll2023!Backend_Prod",
            "jdbc:mysql://localhost:6306/xiaolandb_1.0?serverTimezone=Asia/Shanghai&characterEncoding=UTF-8&&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true"));
        System.out.println(encryptPwd("Ll2023!Backend_Prod", "localhost"));
        System.out.println(encryptPwd("Ll2023!Backend_Prod",
            "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAILRpQLdSRpUFzDEBq0at6cZ+syhiafOEgEC/oKAnUYZ7 linlannet@foxmail.com"));
        System.out.println(encryptPwd("Ll2023!Backend_Prod",
            "b3BlbnNzaC1rZXktdjEAAAAACmFlczI1Ni1jdHIAAAAGYmNyeXB0AAAAGAAAABD/G0KBa9lY+/BxHcOQpXdkAAAAEAAAAAEAAAAzAAAAC3NzaC1lZDI1NTE5AAAAILRpQLdSRpUFzDEBq0at6cZ+syhiafOEgEC/oKAnUYZ7AAAAoL7pgfVXjv1KNYQ1lwnYs+fF6tvLds1ob3kQwj2AUASv4YwCNe7FtROiO6OOVTVbNT6N3bRz3aJ3/zrazIvvj6j8vOOrEPoc+3o+4kKVacjlXSWXGPA3XzpAxOoUKdxSQTOUKdP6uyoB3+vLCSbj3CHWIbbZ+H4sd1USsp+7tPG+OPfbCHTY9xcDJIZYJ2s/qkXxWnqzCosURrzpuT0MC9A="));

    }
}
