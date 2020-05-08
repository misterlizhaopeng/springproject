package basic_type;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class TestCal {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);

        redisTemplate.opsForValue().set("i", "9");
        printCurrValue(redisTemplate,"i");

        redisTemplate.opsForValue().increment("i",1);
        printCurrValue(redisTemplate,"i");

        redisTemplate.getConnectionFactory().getConnection().decr(redisTemplate.getKeySerializer().serialize("i"));
        printCurrValue(redisTemplate,"i");

        redisTemplate.getConnectionFactory().getConnection().decrBy(redisTemplate.getKeySerializer().serialize("i"), 6);
        printCurrValue(redisTemplate, "i");

        redisTemplate.opsForValue().increment("i", 2.3);
        printCurrValue(redisTemplate, "i");
    }


    /**
     * 打印当前key的值
     *
     * @param redisTemplate
     *            spring RedisTemplate
     * @param key 键
     */
    public static void printCurrValue(RedisTemplate redisTemplate, String key) {
        String i = (String) redisTemplate.opsForValue().get(key);
        System.err.println(i);
    }
}
