package jedis_redisTemplate;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class F_ExpireOrders {
    @Test
    public void testExpire(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("redisTemplate.xml");
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);


        redisTemplate.execute((RedisOperations ops) -> {
            ops.boundValueOps("key1").set("value1");
            String keyValue = (String) ops.boundValueOps("key1").get();
            Long expSecond = ops.getExpire("key1");
            System.err.println(expSecond);
            boolean b = false;
            b = ops.expire("key1", 120L, TimeUnit.SECONDS);
            b = ops.persist("key1");
            Long l = 0L;
            l = ops.getExpire("key1");
            Long now = System.currentTimeMillis();
            Date date = new Date();
            date.setTime(now + 120000);
            ops.expireAt("key", date);
            return null;
        });
    }
}
