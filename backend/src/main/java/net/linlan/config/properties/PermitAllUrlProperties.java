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
package net.linlan.config.properties;

import java.util.*;
import java.util.regex.Pattern;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import lombok.extern.slf4j.Slf4j;

import net.linlan.annotation.AuthIgnore;
import net.linlan.commons.script.regex.RegexUtils;

/**
 * 设置Anonymous注解允许匿名访问的url
 * 在Controller文件增加@AuthIgnore，该文件内全部方法都开通匿名访问
 * 在具体方法上method增加@AuthIgnore，仅限该接口匿名访问
 *
 * @author Linlan
 */
@Slf4j
@Configuration
public class PermitAllUrlProperties implements InitializingBean, ApplicationContextAware {
    private static final Pattern PATTERN  = Pattern.compile("\\{(.*?)\\}");

    private ApplicationContext   applicationContext;

    private List<String>         urls     = new ArrayList<>();

    public String                ASTERISK = "*";

    @Override
    public void afterPropertiesSet() {
        RequestMappingHandlerMapping mapping = applicationContext
            .getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);
            // 获取类上边的注解, 替代path variable 为 *
            AuthIgnore controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(),
                AuthIgnore.class);
            Optional.ofNullable(controller)
                .ifPresent(api -> Objects.requireNonNull(info.getPathPatternsCondition())
                    .getPatterns().forEach(url -> urls
                        .add(RegexUtils.replaceAll(PATTERN, url.getPatternString(), ASTERISK))));

            // 获取方法上边的注解 替代path variable 为 *
            AuthIgnore method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(),
                AuthIgnore.class);
            Optional.ofNullable(method)
                .ifPresent(apiMethod -> Objects.requireNonNull(info.getPathPatternsCondition())
                    .getPatterns().forEach(url -> urls
                        .add(RegexUtils.replaceAll(PATTERN, url.getPatternString(), ASTERISK))));
        });

        //Lamda表达式和传统代码的验证
        //        for (RequestMappingInfo info : map.keySet())
        //        {
        //            HandlerMethod handlerMethod = map.get(info);
        //            AuthIgnore controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), AuthIgnore.class);
        //            if (ObjectUtils.isNotEmpty(controller))
        //            {
        //                if (ObjectUtils.isNotEmpty(info))
        //                {
        //                    PathPatternsRequestCondition patternsRequestCondition = info.getPathPatternsCondition();
        //                    if (ObjectUtils.isNotEmpty(patternsRequestCondition))
        //                    {
        //                        Set<PathPattern> urlSet = patternsRequestCondition.getPatterns();
        //                        if (ObjectUtils.isNotEmpty(urlSet))
        //                        {
        //                            urlSet.forEach(url -> urls.add(RegexUtils.replaceAll(PATTERN, url.getPatternString(), ASTERISK)));
        //                        }
        //                    }
        //                }
        //            }
        //        }
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
