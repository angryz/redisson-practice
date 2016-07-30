package angryz.practice.redisson;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzp on 7/30/16.
 */
@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Bean
    RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("192.168.99.100:6379").setConnectionPoolSize(2).setConnectionMinimumIdleSize(1);
        return Redisson.create(config);
    }

    @Bean
    CacheManager cacheManager(@Autowired RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();
        config.put("testCache", new CacheConfig(30 * 60 * 1000, 15 * 60 * 1000));
        return new RedissonSpringCacheManager(redissonClient, config);
    }
}
