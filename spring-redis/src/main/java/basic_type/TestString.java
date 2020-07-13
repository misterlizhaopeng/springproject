package basic_type;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class TestString {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);

//        Long add = redisTemplate.opsForSet().add("课程", "如提高性能优化的课程", "如何优化redis，提高ha高可用");

        Set set_kc = redisTemplate.opsForSet().members("课程");
        set_kc.forEach(a->{
            System.out.println("-------------------------------------->"+a);
        });


        // 设值
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForValue().set("key2", "value2");

        String [] strArr={"b","c","d"};
        List<String> list = Arrays.asList(strArr);
        List list1 = redisTemplate.opsForValue().multiGet(list);
        System.out.println("----------------------------------------------list->start");
        list1.forEach(a->{
            System.out.println(a);
        });
        System.out.println("----------------------------------------------list->end");


        // 通过key获取值
        String value1 = (String) redisTemplate.opsForValue().get("key1");
        System.out.println(value1);
        // 通过key删除值
        redisTemplate.delete("key1");

        // 求长度
        Long length = redisTemplate.opsForValue().size("key2");
        System.out.println(length);//长度根据序列化的对象不同长度不同

        // 设值新值并返回旧值
        String oldValue2 = (String) redisTemplate.opsForValue().getAndSet("key2", "new_value2");
        System.out.println(oldValue2);
        // 通过key获取值.
        String value2 = (String) redisTemplate.opsForValue().get("key2");
        System.out.println(value2);

        // 求子串
        String rangeValue2 = redisTemplate.opsForValue().get("key2", 0, 3);
        System.out.println(rangeValue2);

        // 追加字符串到末尾，返回新串长度
        int newLen = redisTemplate.opsForValue().append("key2", "_app");
        System.out.println(newLen);
        System.out.println(redisTemplate.opsForValue().get("key2"));

    }
}
