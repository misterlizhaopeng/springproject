package jedis_redisTemplate;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.List;

public class C_Transaction {

    @Test
    public void testTx() {


        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);


        String va =  (String) redisTemplate.execute((RedisOperations ops) -> {
            ops.multi();
            ops.boundValueOps("key1").set("value1");
            // 注意由于命令只是进入队列，而没有被执行，所以此处采用get命令，而value却返回为null
            String value = (String) ops.boundValueOps("key1").get();
            System.out.println("事务执行过程中，命令入队列，而没有被执行，所以value为空：value=" + value);
            // 此时list会保存之前进入队列的所有命令的结果
            List list = ops.exec();// 执行事务
            // 事务结束后，获取value1
            return  redisTemplate.opsForValue().get("key1");
        });

        System.out.println(va);


    }
}
