package jedis_redisTemplate;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class D_Pipeline {

    /*
    JavaApi 测试 redis 管道技术的性能：

        redis 每秒操作：66887 次数;
    */

    @Test
    public void testPipeline(){
        Jedis jedis = new Jedis("192.168.25.140", 6380);
        jedis.auth("lp");//登录redis密码验证
        Pipeline pipelined = jedis.pipelined();//redis管道技术
        int i = 0;
        try {
            long start = System.currentTimeMillis();
            while (true) {
                long end = System.currentTimeMillis();
                if (end - start >= 1000) break;

                i++;
                //具体操作
                //jedis.set("key__" + i, "value_" + i);
                pipelined.set("key_b" + i, "value_" + i);
            }
        } catch (Exception e) {

        } finally {
            //jedis.close();
        }

        List<Object> objects = pipelined.syncAndReturnAll();
        System.out.println("redis 每秒操作：" + i + " 次数");
    }

    // java api操作redis的管道技术实现set、get-from-bk
    @Test
    public void testJedisPipeline(){
        JedisPool pool = getPool();
        Jedis jedis = pool.getResource();
        jedis.auth("lp");
        long start = System.currentTimeMillis();
        // 开启流水线
        Pipeline pipeline = jedis.pipelined();
        // 这里测试10万条的读写2个操作
        for (int i = 0; i < 20000; i++) {
            int j = i + 1;
            pipeline.set("pipeline_key_" + j, "pipeline_value_" + j);
            pipeline.get("pipeline_key_" + j);
        }
        // pipeline.sync();//这里只执行同步，但是不返回结果
        // pipeline.syncAndReturnAll();将返回执行过的命令返回的List列表结果
        List result = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        // 计算耗时
        System.err.println("耗时：" + (end - start) + "毫秒");
    }

    private static JedisPool getPool() {
        JedisPoolConfig poolCfg = new JedisPoolConfig();
        // 最大空闲数
        poolCfg.setMaxIdle(50);
        // 最大连接数
        poolCfg.setMaxTotal(100);
        // 最大等待毫秒数
        poolCfg.setMaxWaitMillis(20000);

        // 使用配置创建连接池
        JedisPool pool = new JedisPool(poolCfg, "192.168.25.140",6380);
        // 从连接池中获取单个连接
        Jedis jedis = pool.getResource();
        // 如果需密码
        //jedis.auth("lp");
        return pool;
    }

    // spring 操作redis的管道技术实现set、get-from-bk
    @Test
    public void testPipelineBySpring(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);

        // 使用Java8的Lambda表达式
        SessionCallback callBack = (SessionCallback) (RedisOperations ops) -> {
            for (int i = 0; i < 100000; i++) {
                int j = i + 1;
                ops.boundValueOps("pipeline_key_c" + j).set("pipeline_value_" + j);
                ops.boundValueOps("pipeline_key_c" + j).get();
            }
            return null;
        };

        long start = System.currentTimeMillis();
        // 执行Redis的流水线命令
        List resultList = redisTemplate.executePipelined(callBack);
        long end = System.currentTimeMillis();
        System.err.println("耗时：" + (end - start) + "毫秒");

    }
}
