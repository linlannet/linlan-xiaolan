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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONFactory;

import lombok.extern.slf4j.Slf4j;

import net.linlan.commons.core.StringUtils;
import static com.alibaba.fastjson2.JSONWriter.Feature.WriteClassName;

/**
 * redis配置
 * 
 * @author Linlan
 */
@Slf4j
@Configuration
@EnableCaching
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${platform.cache.prefix:xiaolan}")
    private String prefix;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        //序列化
        RedisSerializer<String> redisSerializer = new StringRedisSerializer(String.class, prefix);
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer<>(
            Object.class);
        //redis缓存配置
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
            .defaultCacheConfig()
            // 默认redis cache 缓存时间1天
            .entryTtl(Duration.ofDays(1))
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
            .serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(fastJsonRedisSerializer));

        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(redisCacheConfiguration).build();
        return redisCacheManager;
    }

    /**
     * 设置 redis 数据默认过期时间，默认2小时
     * 设置@cacheable 序列化方式
     * @return 缓存配置
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(
            Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer))
            .entryTtl(Duration.ofDays(7));
        return configuration;
    }

    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        //序列化
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(
            Object.class);
        // value值的序列化采用fastJsonRedisSerializer
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        // 全局开启AutoType，这里方便开发，使用全局的方式
        // 设置全局的解析特性
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept("net.linlan");
        // 建议使用这种方式，小范围指定白名单
        // ParserConfig.getGlobalInstance().addAccept("net.linlan.*.entity");
        // key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer(String.class, prefix));
        template.setHashKeySerializer(new StringRedisSerializer(String.class, prefix));
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * 自定义缓存key生成策略，默认将使用该策略
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            Map<String, Object> container = new HashMap<>(3);
            Class<?> targetClassClass = target.getClass();
            // 类地址
            container.put("class", targetClassClass.toGenericString());
            // 方法名称
            container.put("methodName", method.getName());
            // 包名称
            container.put("package", targetClassClass.getPackage());
            // 参数列表
            for (int i = 0; i < params.length; i++) {
                container.put(String.valueOf(i), params[i]);
            }
            // 转为JSON字符串
            String jsonString = JSON.toJSONString(container);
            // 做SHA256 Hash计算，得到一个SHA256摘要作为Key
            return DigestUtils.sha256Hex(jsonString);
        };
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        // 异常处理，当Redis发生异常时，打印日志，但是程序正常走
        log.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheGetError：key -> [{}]", key, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key,
                                            Object value) {
                log.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value,
                    e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheEvictError：key -> [{}]", key, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("Redis occur handleCacheClearError：", e);
            }
        };
    }

}

/**
 * Value 序列化
 *
 * @param <T>   泛型
 */
class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    private final Class<T> clazz;

    FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, WriteClassName).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public T deserialize(byte[] bytes) {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, StandardCharsets.UTF_8);
        return JSON.parseObject(str, clazz);
    }

}

/**
 * 重写序列化器
 * @param <T>   泛型
 */
class StringRedisSerializer<T> implements RedisSerializer<T> {

    private final String   prefix;

    private final Charset  charset;

    private final Class<T> clazz;

    StringRedisSerializer(Class<T> clazz) {
        this(clazz, StandardCharsets.UTF_8, null);
    }

    StringRedisSerializer(Class<T> clazz, String prefix) {
        this(clazz, StandardCharsets.UTF_8, prefix);
    }

    private StringRedisSerializer(Class<T> clazz, Charset charset, String prefix) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
        this.prefix = StringUtils.isNotBlank(prefix) ? prefix + ":" : "";
        this.clazz = clazz;
    }

    @Override
    public T deserialize(byte[] bytes) {
        String key = (bytes == null ? null : new String(bytes, charset));
        int indexOf = key.indexOf(prefix);
        if (indexOf > -1) {
            key = key.substring(indexOf);
        }
        return (T) key;
    }

    @Override
    public byte[] serialize(T object) {
        String string;
        if (object instanceof String) {
            string = JSON.toJSONString(object);
        } else {
            string = JSON.toJSONString(object);
        }
        if (StringUtils.isBlank(string)) {
            return null;
        }
        if (StringUtils.isBlank(prefix)) {
            string = string.replace("\"", "");
        } else {
            string = string.replace("\"", "");
            if (!string.startsWith(prefix)) {
                string = prefix + string;
            }
        }
        return string.getBytes(charset);
    }
}
