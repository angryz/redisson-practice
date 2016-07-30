package angryz.practice.redisson;

import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzp on 7/30/16.
 */
@Service
@CacheConfig(cacheNames = "test")
public class MockService {

    private static Map<Long, CachedObject> DB = new HashMap<>(3);

    @Cacheable(key = "#id")
    public CachedObject find(long id) throws InterruptedException {
        System.out.println("Finding...");
        Thread.sleep(2000);
        return DB.get(id);
    }

    @CachePut(key = "#object.id")
    public CachedObject insert(CachedObject object) throws InterruptedException {
        System.out.println("Inserting...");
        DB.put(object.getId(), object);
        Thread.sleep(500);
        return object;
    }

    @Caching(
            evict = {@CacheEvict(key = "#object.id")},
            put = {@CachePut(key = "#object.id")}
    )
    public CachedObject update(CachedObject object) throws InterruptedException {
        System.out.println("Updating...");
        DB.put(object.getId(), object);
        Thread.sleep(500);
        return object;
    }

    @CacheEvict(key = "#id")
    public void delete(long id) throws InterruptedException {
        System.out.println("Deleting...");
        DB.remove(id);
        Thread.sleep(500);
    }

}
