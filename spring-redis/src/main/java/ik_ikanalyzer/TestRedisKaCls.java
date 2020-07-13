package ik_ikanalyzer;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class TestRedisKaCls {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);

//        Long add = redisTemplate.opsForSet().add("课程", "如提高性能优化的课程", "如何优化redis，提高ha高可用");
//
//        Set set_kc = redisTemplate.opsForSet().members("课程");
//        set_kc.forEach(a->{
//            System.out.println("-------------------------------------->"+a);
//        });


        String str1 = "学生张三提出了一些列的问题，这里，我想给大家讲解一下这些问题如何解决，以来解决大家困惑依旧的问题，免得大家再去学习一些浪费生命的课程";
        String title1 = "如何优化redis，提高ha高可用";

        String str2 = "这是一个比较好的课程，你们说是不是";
        String title2 = "提高性能优化的课程";

        invertedIndexes(title1, str1);
        invertedIndexes(title2, str2);
    }

    //显示所有的集合的key
    @Test
    public void showAllSets() {
        String str1 = "学生张三提出了一些列的问题，这里，我想给大家讲解一下这些问题如何解决，以来解决大家困惑依旧的问题，免得大家再去学习一些浪费生命的课程";
        String str2 = "这是一个比较好的课程，你们说是不是";
        String k = getKeywords(str1, true);
        System.out.println(k);
        String k2 = getKeywords(str2, true);
        System.out.println(k2);

    }

    // searck
    @Test
    public void getByInvertedIndex() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        Set set = redisTemplate.opsForSet().members("课程");
        set.forEach(a -> {
            System.out.println(a);
        });

    }

    public static void invertedIndexes(String title, String content) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);

        String k = getKeywords(content, true);
        System.out.println(k);
        String[] sp = k.split(",");
        List<String> ls = Arrays.asList(sp);
        ls.forEach(a -> {
            redisTemplate.opsForSet().add(a, title);
        });

    }

    /**
     * @param keyword 源词汇
     * @param smart   是否智能分词 这里输入true
     * @return 分词词组(, 拼接)
     */
    public static String getKeywords(String keyword, boolean smart) {
        StringReader reader = new StringReader(keyword);
        IKSegmenter iks = new IKSegmenter(reader, smart);
        StringBuilder buffer = new StringBuilder();
        try {
            Lexeme lexeme;
            while ((lexeme = iks.next()) != null) {
                buffer.append(lexeme.getLexemeText()).append(',');
            }
        } catch (IOException e) {
        }
        //去除最后一个,
        if (buffer.length() > 0) {
            buffer.setLength(buffer.length() - 1);
        }
        return buffer.toString();
    }
}
