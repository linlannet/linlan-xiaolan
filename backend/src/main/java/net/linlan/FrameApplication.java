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
package net.linlan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import jakarta.annotation.PostConstruct;
import net.linlan.config.DynamicDataSourceConfig;

/**
 * Application main for spring boot jar
 * Filename:EntApplication.java
 * Desc: the main entrance of WDC to run, to debug
 *
 * @author Linlan
 * CreateTime:2017-10-15 2:32 PM
 *
 * @version 1.0
 * @since 1.0
 *
 */
@Import({ DynamicDataSourceConfig.class })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@Configuration
@MapperScan(basePackages = { "net.linlan.**.dao" })
@ComponentScan(basePackages = { "net.linlan" })
@EnableScheduling
@EnableAsync
@ServletComponentScan
@EnableEncryptableProperties
public class FrameApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(FrameApplication.class, args);
        System.out.println("------- 一体化平台启动成功 -------");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FrameApplication.class);
    }

    @PostConstruct
    public void suppressPageHelperBanner() {
        // 关闭 PageHelper 的 banner
        System.setProperty("pagehelper.banner", "false");
    }
}
