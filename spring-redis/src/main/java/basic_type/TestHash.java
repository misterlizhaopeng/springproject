package basic_type;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.*;

public class TestHash {



    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate.xml");
    RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);


    @Test
    public void testGet(){
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps("hash-question");
        Map map = boundHashOperations.entries();
        System.out.println(map);

    }



    @Test
    public void hmget(){
        List list = redisTemplate.boundHashOps("hashKey").multiGet(Arrays.asList("f3","f1", "f2"));
        System.out.println(list);
    }



    @Test
    public   void test_01(){

        //hset article:20010280 id 20010290 title 这是一个比较不错的文章，关于redis性能调优的  createtime 1990808 update 158900987
        //hset article:20010270 id 20010290 title 这是一个比较不错的文章，关于redis性能调优的  createtime 1990808 update 158900987
        Map<String,String> m1=new HashMap<>();

        m1.put("id", "20010270");
        m1.put("title", "redis is goog tool");
        m1.put("createtime", new Date().toString());
        m1.put("update", new Date().toString());

//        m1.put("id", "20010290");
//        m1.put("title", "这是一个比较不错的文章，关于redis性能调优的");
//        m1.put("createtime", new Date().toString());
//        m1.put("update", new Date().toString());
        redisTemplate.opsForHash().putAll("article:20010270",m1);


//        Object title = redisTemplate.opsForHash().get("article:20010280", "title");
//        System.out.println(title);



        System.out.println("----------------------------------------------hash->start");
        //redisTemplate.opsForHash().entries()
        Map hashKey = redisTemplate.boundHashOps("hashKey").entries();//
        System.out.println(hashKey);
        System.out.println("----------------------------------------------hash->end");





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
