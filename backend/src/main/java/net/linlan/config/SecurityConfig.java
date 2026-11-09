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

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

import lombok.AllArgsConstructor;

import jakarta.annotation.Resource;
import net.linlan.authn.sms.security.MobileAuthenticationProvider;
import net.linlan.authn.sms.security.MobileUserDetailsService;
import net.linlan.authn.sms.security.MobileVerifyCodeService;
import net.linlan.config.properties.PermitAllUrlProperties;
import net.linlan.frame.comm.security.filter.JwtAuthenticationTokenFilter;
import net.linlan.frame.comm.security.handle.AuthenticationEntryPointImpl;
import net.linlan.frame.comm.security.handle.LogoutSuccessHandlerImpl;
import net.linlan.social.manage.security.ThirdAuthenticationProvider;
import net.linlan.social.manage.security.ThirdOpenIdService;
import net.linlan.social.manage.security.ThirdUserDetailsService;

/**
 * spring security配置
 *
 * @author Linlan
 */
@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    /**
     * 自定义用户认证逻辑
     */
    @Resource
    private UserDetailsService           userDetailsService;
    @Resource
    private MobileUserDetailsService     mobileUserDetailsService;
    @Resource
    private MobileVerifyCodeService      mobileVerifyCodeService;
    @Resource
    private ThirdUserDetailsService      thirdUserDetailsService;
    @Resource
    private ThirdOpenIdService           thirdOpenIdService;
    @Resource
    private ApplicationEventPublisher    applicationEventPublisher;
    /**
     * 认证失败处理类
     */
    @Resource
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    /**
     * 退出处理类
     */
    @Resource
    private LogoutSuccessHandlerImpl     logoutSuccessHandler;

    /**
     * token认证过滤器
     */
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 跨域过滤器
     */
    @Resource
    private CorsFilter                   corsFilter;

    /**
     * 允许匿名访问的地址
     */
    @Resource
    private PermitAllUrlProperties       permitAllUrlProperties;

    /**
     * 多身份验证实现，增加用户名密码、手机短信、第三方授权认证三种方式
     * @return 身份验证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providerList = new ArrayList<>();
        providerList.add(daoAuthenticationProvider());
        providerList.add(mobileAuthenticationProvider());
        providerList.add(thirdAuthenticationProvider());
        ProviderManager providerManager = new ProviderManager(providerList);
        providerManager.setAuthenticationEventPublisher(
            new DefaultAuthenticationEventPublisher(applicationEventPublisher));
        return providerManager;
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public MobileAuthenticationProvider mobileAuthenticationProvider() {
        return new MobileAuthenticationProvider(mobileUserDetailsService, mobileVerifyCodeService);
    }

    @Bean
    public ThirdAuthenticationProvider thirdAuthenticationProvider() {
        return new ThirdAuthenticationProvider(thirdUserDetailsService, thirdOpenIdService);
    }

    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     * @param httpSecurity  HTTP安全链
     * @return  安全配置
     * @throws Exception    异常
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            // CSRF禁用，因为不使用session
            .csrf(csrf -> csrf.disable())
            // 禁用HTTP响应标头
            .headers((headersCustomizer) -> {
                headersCustomizer.cacheControl(cache -> cache.disable())
                    .frameOptions(options -> options.sameOrigin());
            })
            // 认证失败处理类
            .exceptionHandling(
                exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
            // 基于token，所以不需要session
            .sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // 注解标记允许匿名访问的url
            .authorizeHttpRequests((requests) -> {
                permitAllUrlProperties.getUrls()
                    .forEach(url -> requests.requestMatchers(url).permitAll());
                // 对于登录login 注册register 和开放类接口api/open/等 允许匿名访问
                requests
                    .requestMatchers("/login", "/third/ecorgan/login", "/platLogin", "/register",
                        "/api/open/**", "/login/**", "/register/**")
                    .permitAll()
                    // 静态资源，可匿名访问
                    .requestMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css",
                        "/**/*.js", "/profile/**")
                    .permitAll()
                    .requestMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**",
                        "/*/api-docs", "/druid/**")
                    .permitAll()
                    // 除上面外的所有请求全部需要鉴权认证
                    .anyRequest().authenticated();
            })
            // 添加Logout filter
            .logout(
                logout -> logout.logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler))
            // 添加JWT filter
            .addFilterBefore(jwtAuthenticationTokenFilter,
                UsernamePasswordAuthenticationFilter.class)
            // 添加CORS filter
            .addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class)
            .addFilterBefore(corsFilter, LogoutFilter.class).build();
    }

    /**
     * 强散列哈希加密实现
     * @return BCrypt密码解析器
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
