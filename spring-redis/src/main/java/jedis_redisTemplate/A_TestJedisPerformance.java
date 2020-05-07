package jedis_redisTemplate;

import redis.clients.jedis.Jedis;

public class A_TestJedisPerformance {

    /**
     * 测试一秒 jedis 添加数据的数量：只有上千次；
     *
     * @param args
     */
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.25.140", 6380);
        jedis.auth("lp");//登录redis密码验证
        int i = 0;
        try {
            long start = System.currentTimeMillis();
            while (true) {
                long end = System.currentTimeMillis();
                if (end - start >= 1000) break;

                i++;
                //具体操作
                jedis.set("key__" + i, "value_" + i);
            }
        } catch (Exception e) {

        } finally {
            jedis.close();
        }
        System.out.println("redis 每秒操作：" + i + " 次数");

        //redis 每秒操作：2477 次数
        /*
注意：
java 有多种redis的api，比如Jredis、Lettuce等，为了融合不同的api，spring做了封装，提供了一个统一的接口：RedisConnection；
总结jedis：
    1.可以看出每秒操作的次数只有上千次，很慢，原因就是一条一条发送命令给redis去执行，再加上我redis部署的是虚拟机，其实有更快的方法：管道技术；
    2.这里的代码可以看出只是一个jedis连接，更多我们会使用连接池去管理jedis的连接；
    3.如何把一个对象发送到redis，其实，我们也可以把一个对象序列化set到redis，再从redis获取到反序列化即可，但这还是存在比较大的工作量的，
        此时就需要更好的封装技术Spring的模板：Redistemplate，让spring帮我们封装了序列化、反序列化对象；
    4.在spring中如何使用redis模板对象RedisTemplate
        1.除了使用jedis.jar包，下面maven依赖：
             <dependency>
                <groupId>redis.clients</groupId>
                <artifactId>jedis</artifactId>
                <version>2.9.0</version>
            </dependency>

        ，还需要spring-data-redis.jar，下面maven依赖：
            <dependency>
                <groupId>org.springframework.data</groupId>
                <artifactId>spring-data-redis</artifactId>
                <version>2.1.2.RELEASE</version>
            </dependency>

        2.具体配置jedis、spring-data-redis，或者说如何具体配置RedisTemplate：
            2.1 配置连接池
            2.2 配置连接工厂
            2.3 给RedisTemplate配置徐序列化接口
            见测试代码：B_TestRedisTemplate
                注意：
                通过上面封装RedisTemplate，在set、get的操作的时候，可能来自连接池中的不同连接，下面两个接口可以实现一个连接操作redis，常用的是接口SessionCallback ：
                    在B_TestRedisTemplate中，使用接口 SessionCallback，进行把多个redis的操作命令放到一个redis连接中；
                    其中RedisCallback比较底层，所以更多的使用SessionCallback；
        * */
    }
}
