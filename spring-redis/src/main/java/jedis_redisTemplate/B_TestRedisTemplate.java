package jedis_redisTemplate;

import bean.Role;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.ArrayList;
import java.util.List;

public class B_TestRedisTemplate {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        //jedis.opsForValue().set("role", new Role(1,"角色名称","注释"));
        //redisTemplate.opsForValue().set("role1","abc_role1");

        Role role = (Role) redisTemplate.opsForValue().get("role");
        System.out.println(role);
        Object role1 = (String) redisTemplate.opsForValue().get("role1");
        System.out.println(role1);
    }

    /**
     * 测试 RedisTemplate 一秒添加数据的数量
     */
    @Test
    public void testRedisTemplate() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);


        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.forEach(a -> {
            System.out.println(a);
        });

        int i = 0;
        try {
            long start = System.currentTimeMillis();
            while (true) {
                long end = System.currentTimeMillis();
                if (end - start >= 1000) break;
                i++;
                //具体操作
                redisTemplate.opsForValue().set("keya" + i, "value_" + i);
            }
        } catch (Exception e) {

        } finally {

        }
        System.out.println("redis 每秒操作：" + i + " 次数");
    }


    /**
     * 通过接口SessionCallback，把多个redis命令放到一个redis连接中，
     */
    @Test
    public void testRedisTemplate_SameConnection() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        Role role = new Role(1, "角色名称", "注释");
        SessionCallback<Role> sessionCallback = new SessionCallback<Role>() {
            @Override
            public Role execute(RedisOperations operations) throws DataAccessException {
                operations.boundValueOps("role_1").set(role);
                return (Role) operations.boundValueOps("role_1").get();
            }
        };
        Role r = (Role) redisTemplate.execute(sessionCallback);
        System.out.println(r);


    }
}
