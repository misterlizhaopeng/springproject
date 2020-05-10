package mybatis_spring_redis.service.impl;

import mybatis_spring_redis.service.RedisTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.List;

// 总结：管道技术、或者事务技术，redis客户端的多个命令都是一次放入队列中的，把队列的命令一次性发送到redis服务，减少网络传输，提高数据处理的性能
@Service
public class RedisTemplateServiceImpl implements RedisTemplateService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 执行管道技术，将多个命令一次性发送给redis服务器
     */
    @Override
    public void exePipeline() {
        redisTemplate.executePipelined((RedisOperations op)->{
            //注意，在管道技术下，命令不会马上返回结果，结果是一次性执行后返回的
            op.boundValueOps("key1").set("abc");
            op.boundHashOps("hash").put("hash-k-1", "hash-v-1");
            op.opsForValue().get("key1");
            return null;
        });

    }

    /**
     * 使用SessionCallback 接口实事务在一个redis连接中执行
     */
    @Override
    public void exeTransaction() {
        List list = (List)redisTemplate.execute((RedisOperations op) -> {
            //监控
            op.watch("key1");
            //开始事务
            op.multi();
            //注意，命令都不会被马上执行，只会放到队列中，只会返回null
            op.boundValueOps("key1").set("abc");
            op.boundHashOps("hash").put("hash-k-1", "hash-v-1");
            op.opsForValue().get("key1");
            List result = op.exec();
            return result;
        });
        System.out.println(list);
    }

    /**
     * 使用SessionCallback 接口实现多个命令在一个redis连接中执行
     */
    @Override
    public void exeMultiCommand() {
        Object obj = redisTemplate.execute((RedisOperations op) -> {
            op.boundValueOps("key1").set("abc");
            op.boundHashOps("hash").put("hash-k-1", "hash-v-1");
            return op.boundValueOps("key1").get();
        });
        System.out.println(obj);

    }
}
