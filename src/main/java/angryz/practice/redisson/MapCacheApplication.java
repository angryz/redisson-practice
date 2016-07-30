package angryz.practice.redisson;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zzp on 7/30/16.
 */
public class MapCacheApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:app.xml");
        CacheManager cacheManager = (CacheManager) context.getBean("cacheManager");
        Cache cache = cacheManager.getCache("test");
        MockService service = context.getBean(MockService.class);
        CachedObject insertResult = service.insert(new CachedObject(1l, "one"));
        System.out.println("Inserted " + insertResult);
        System.out.println(cache.getNativeCache());
        System.out.println();
        CachedObject findResult = service.find(1);
        System.out.println("Found " + findResult);
        System.out.println(cache.getNativeCache());
        System.out.println();
        CachedObject updateResult = service.update(new CachedObject(1l, "two"));
        System.out.println("Updated " + updateResult);
        System.out.println(cache.getNativeCache());
        System.out.println();
        service.delete(111);
        System.out.println("Deleted");
        System.out.println(cache.getNativeCache());
    }
}
