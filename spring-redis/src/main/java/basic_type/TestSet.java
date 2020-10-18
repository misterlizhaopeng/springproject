package basic_type;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

import org.junit.Test;

/**
 * @ClassName basic_type.TestSet
 * @Deacription TODO
 * @Author LP
 * @Date 2020/10/16 17:18
 * @Version 1.0
 **/
public class TestSet {

    public static final Integer _COUNT_INTEGER = 2000;
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate.xml");
    RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);

    /**
     * 生成题库
     */
    @Test
    public void testAddHash() {
        for (int i = 1; i <= _COUNT_INTEGER; i++) {
            Random random = new Random(5);
            int max = 5, min = 3;
            int count = random.nextInt(max - min + 1) + min;
            List<String> list = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < count; j++) {
                sb.append("答案_" + j + ",");

            }
            String substring = sb.substring(0, sb.length() - 1);
            //正确答案模拟

            int ana = 0;
            substring += ("【】" + 1);

            //这是一个什么问题呢？【】答案_0,答案_1,答案_2,答案_3,答案_4【】1,2
            redisTemplate.boundHashOps("hash-question").put(String.valueOf(i), i + "【】这是一个什么问题呢？【】" + substring);

        }

    }

    /**
     * 生成题库
     */
    @Test
    public void testAddSet() {

        for (int i = 0; i < _COUNT_INTEGER; i++) {
            redisTemplate.boundSetOps("set-question").add(String.valueOf(i));
        }
    }


    /**
     * 随机获取5个题目以及对应的答案
     */
    @Test
    public void testRandMember() {
        List list = redisTemplate.boundSetOps("set-question").randomMembers(5);
        list.forEach(
                a -> {
                    System.out.println(a);
                }
        );
        List list1 = redisTemplate.boundHashOps("hash-question").multiGet(list);
        list1.forEach(
                a -> {
                    System.out.println(a);
                });
    }

    /**
     * 答题人的对应的题目：
     * perid-
     */
    @Test
    public void perAnswer() {
        //每一次提交，表示一轮答题情况：[perid:1,answer:[{1171:[1,2]},{1172:[2]}]]

        Map<String, String> map = new HashMap<>();

        redisTemplate.boundHashOps("perid:100").putIfAbsent("", "");//.putAll(map);
    }


    /**
     * 答题的情况
     */
    @Test
    public void andCondition() {

        // hash :
        // key:answer-condition:value:{k:v}

        //redisTemplate.boundHashOps("answer-condition").put("answ");
    }

    @Test
    public void testRandom() {
        Random random = new Random();
        int max = 5, min = 4;
        int i1 = random.nextInt(max - min + 1) + min;
        System.out.println(i1);
    }


}

