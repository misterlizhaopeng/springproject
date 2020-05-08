package basic_type;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.*;

public class TestHash {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);



        String key = "hash";
        Map<String, String> map = new HashMap<String, String>();
        map.put("f1", "val1");
        map.put("f2", "val2");

        // 相当于hmset命令
        redisTemplate.opsForHash().putAll(key, map);
        // 相当于hset命令
        redisTemplate.opsForHash().put(key, "f3", "6");
        printValueForhash(redisTemplate, key, "f3");

        // 相当于 hexists key filed命令
        boolean exists = redisTemplate.opsForHash().hasKey(key, "f3");
        System.out.println(exists);

        // 相当于hgetall命令
        Map keyValMap = redisTemplate.opsForHash().entries(key);
        System.out.println(keyValMap);

        // 相当于 hincrby 命令
        redisTemplate.opsForHash().increment(key, "f3", 2);
        printValueForhash(redisTemplate, key, "f3");


        // 相当于hincrbyfloat命令
        redisTemplate.opsForHash().increment(key, "f3", 0.88);
        printValueForhash(redisTemplate, key, "f3");

        // 相当于hvals命令
        List valueList = redisTemplate.opsForHash().values(key);
        // 相当于hkeys命令
        Set keyList = redisTemplate.opsForHash().keys(key);
        List<String> fieldList = new ArrayList<String>();
        fieldList.add("f1");
        fieldList.add("f2");

// 相当于hmget命令
        List valueList2 = redisTemplate.opsForHash().multiGet(key, keyList);

        // 相当于hsetnx命令
        boolean success = redisTemplate.opsForHash().putIfAbsent(key, "f4", "val4");
        System.out.println(success);
        // 相当于hdel命令
        Long result = redisTemplate.opsForHash().delete(key, "f1", "f2");
        System.out.println(result);










    }


    private static void printValueForhash(RedisTemplate redisTemplate, String key, String field) {
        // 相当于hget命令
        Object value = redisTemplate.opsForHash().get(key, field);
        System.out.println(value);
    }
}
