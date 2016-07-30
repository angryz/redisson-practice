package angryz.practice.redisson;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * Created by zzp on 7/30/16.
 */
public class RedissonClientFactoryBean extends AbstractFactoryBean<RedissonClient> {

    @Override
    public Class<?> getObjectType() {
        return RedissonClient.class;
    }

    @Override
    protected RedissonClient createInstance() throws Exception {
        Config config = new Config();
        config.useSingleServer().setAddress("192.168.99.100:6379");
        return Redisson.create(config);
    }
}
