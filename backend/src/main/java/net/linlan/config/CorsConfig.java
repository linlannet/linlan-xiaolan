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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * Filename:CorsConfig.java
 * Desc:跨域配置注入类
 *
 * @author Linlan
 * CreateTime:2018/6/6 17:58
 *
 * @version 1.0
 * @since 1.0
 *
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${spring.profiles.active}")
    private String   activeMode;
    @Value("${platform.prop.allowedOrigins}")
    private String[] allowedOrigins;

    /**
     * 跨域配置
     * @return 跨域配置信息
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 可信域配置
        if (allowedOrigins != null && allowedOrigins.length > 0) {
            for (String allowedOrigin : allowedOrigins) {
                config.addAllowedOriginPattern(allowedOrigin);
            }
        } else {
            // 设置访问源地址
            config.addAllowedOriginPattern("*");
        }
        // 设置访问源请求头
        config.addAllowedHeader("*");
        // 设置访问源请求方法
        config.addAllowedMethod("*");
        // 有效期 1800秒
        config.setMaxAge(1800L);
        // 添加映射路径，拦截一切请求
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        // 返回新的CorsFilter
        return new CorsFilter(source);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] origins = { "*" };
        if (activeMode.contains("prod")
        //                || activeMode.contains("test")  //开发调试下，注释，生产发布开启
        ) {
            origins = allowedOrigins;
        }
        registry.addMapping("/**")
            //                .allowedOrigins(origins)
            .allowedOriginPatterns(origins).allowCredentials(true)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").maxAge(3600);
    }
}
