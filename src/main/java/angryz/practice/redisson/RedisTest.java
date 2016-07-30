package angryz.practice.redisson;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.core.RAtomicLong;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonCache;
import org.redisson.spring.cache.RedissonSpringCacheManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzp on 7/30/16.
 */
public class RedisTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("192.168.99.100:6379").setConnectionPoolSize(2).setConnectionMinimumIdleSize(1);
        Redisson client = (Redisson) Redisson.create(config);
        RAtomicLong along = client.getAtomicLong("ALong");
        long a = along.addAndGet(7);
        System.out.println(a);


        Map<String, CacheConfig> cacheConfigMap = new HashMap<>();
        cacheConfigMap.put("testCache", new CacheConfig(30 * 60 * 1000, 15 * 60 * 1000));
        RedissonSpringCacheManager cacheManager = new RedissonSpringCacheManager(client, cacheConfigMap);
        RedissonCache cache = (RedissonCache) cacheManager.getCache("testCache");
        cache.put("foo", "bar");
        String foo = cache.get("foo", String.class);
        System.out.println("foo = " + foo);
        cache.evict("foo");
    }
}
