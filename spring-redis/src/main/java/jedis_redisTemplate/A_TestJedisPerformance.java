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

        3.package为 basic_type 下面为redis基本数据类型的联系，暂时只练习了最常用的string、hash 两种类型，以后有时间，再说；
        4.事务
            multi：表示开始redis事务
                set k1 v1
                ...一系列操作
                set k2 v2
            exec：表示执行事务
            注意：
                1.在multi和exec之间的每一个命令，会依次添加到队列中，没有真正的执行，在exec的时候，才真正的执行；
                2.在multi和exec之间的每一个命令，如果存在错误的命令语法，比如incr （对一个key增1，但少写了key），则整个事务执行失败
                3.在multi和exec之间的每一个命令，如果不存在错误的命令语法，而在执行队列的时候错误，比如incr k1（对一个k1的值为一个字符串进行增1），
                    则整个事务中的队列中没问题的命令执行成功，有问题的命令失败；这种事务明显和mysql等关系型数据库的事务不一样，
                    redis这么做就是为了俩字而设计的-性能；
            discard：表示回滚事务，如果multi之后，不想提交队列里面的命令，则可以通过discard命令进行回滚；
            spring-data-redis 在一个redis连接里面测试 redis 事务机制，见测试代码：C_Transaction


            watch 机制：
                在redis事务中，监视一个key的值是否变化，如果没有变化，整个正常的事务正常执行，否则回滚，这也就是乐观锁机制，实现方式为CAS(比较、交换)；
                大致意思就是，在多线程中，对于一个共享数据，每一个线程都可以操作，当一个线程修改这个共享数据之前，先比较一下是否和当前线程的副本的数据一致（比如watch的数据），
                如果一致，可以修改数据，如果不一致，则不能修改该共享数据；

            对于CAS存在ABA的问题，就是当一个线程A监视共享数据之后去执行别的操作没有修改共享数据之前，线程B修改了共享数据为别的值，然后又修改回操作共享数据之前的值，结束
            线程B的操作，然后线程A执行到了修改共享数据的命令，进行CAS判断，共享数据的值的确没有变化，但此时共享数据已被别的线程修改了，对于线程A来说会产生线程安全问题；

            如何解决ABA问题：让每一个线程不仅仅关注共享数据的值，还要关注共享数据的version版本，也就是说，当一个线程修改了共享数据，版本递增；

            在redis的watch中，不存在ABA的问题；

        5.redis管道技术；
            管道技术是一种协议，客户端无法演示；
            见测试代码：D_Pipeline
                JavaApi 测试 redis 管道技术的性能
                java api操作redis的管道技术实现set、get-from-bk
                spring 操作redis的管道技术实现set、get-from-bk
            通过上面的测试，管道技术性能非常高；



        6.发布订阅
        测试代码：E_SubscribePublish、MyRedisMessageListener、redisTemplate-subscribe.xml
                注意：
                    配置监听类、监听容器，才能实现对渠道的监听操作；
                    redis的发布-订阅模式，可以解决很多问题，一个发布者可以对应多个订阅者，比如记账系统完成记账之后，消息系统、邮件系统，微信系统
                    都可以作为订阅者，订阅消息进行消费，如果以后还想增加一个彩信平台接受消息，只需要添加订阅即可；

        7.超时命令：
        测试代码（spring方式、javaapi方式访问redis，此例是spring方式）：F_ExpireOrders
                注意：redis命令超时如何回收：
                    定时回收、惰性回收；
                    定时回收：指在确定的某一个时间触发一段代码，回收超时的键值对，缺点：如果被回收的键的值比较大，在回收的时候，redis会运行较长时间，从而导致卡顿；
                    惰性回收：get请求一个超时的键值对的时候，进行回收；

        8.redis-lua

            从redis的2.6以上版本开始支持lua脚本，由于reids的计算能力并不是很强大，然而lua脚本弥补了这一缺点，不仅仅如此，在redis中，使用lua的另一个优点：
                在redis中，执行lua脚本不会被中断，具有原子性，这个特性有助于redis对并发数据一致性的支持；

            redis支持两种lua代码书写风格：
                1.redis-cli下直接，执行输入lua代码
                2.lua语言编写的脚本
                但是对于一些简单的lua脚本，redis支持缓存脚本，只是它会使用SHA-1算法对脚本进行签名，然后返回SHA-1标识，通过这个标识运行就行了；

            1.redis-cli下直接，执行输入lua代码
                基本格式
                eval lua-script key-num [k1 k2 k3...]  [v1 v2 v3...]
                eval：代表执行lua脚本的命令
                lua-script：代表lua脚本
                key-num：代表脚本中多少个key
                [k1 k2 k3...]：传递给lua的参数
                [v1 v2 v3...]：传递给lua的参数

                例子：
                eval "return 'hello lua'" 0
                    解释："return 'hello lua'"标识lua-script，0标识0个key；
                eval "redis.call('set',KEYS[1],ARGV[1])" 1 CC DD
                    解释："redis.call('set',KEYS[1],ARGV[1])"标识lua-script，1标识1个key，剩下的是ARGV的值；

           2.缓存脚本
                基本格式：
                script load scriptcontent
                这个脚本返回一个SHA-1的字符串，通过这个字符串可以执行这个字符串代表的缓存脚本，缓存脚本的优点，减少网络上redis命令的传输数据；
                缓存脚本使用命令格式
                evalsha SHA-1的字符串  keynum [k1 k2 k3...]  [v1 v2 v3...]
                例子：
                    script load "redis.call('set',KEYS[1],ARGV[1])"
                    evalsha 7cfb4342127e7ab3d63ac05e0d3615fd50b45b06 1 g gV

                通过命令练习总结：缓存脚本只是把lua-script缓存起来，其他的参数在 evalsha 的时候再传递即可，如上面；

                测试代码：H_TestLua
                    简单字符串的存储：redis-cli下直接，执行输入lua代码，缓存脚本形式
                    通过spring提供的RedisScript接口（实现类DefaultRedisScript）对象执行lua脚本操作对象类型的数据；
                    执行lua脚本文件-当lua脚本逻辑比较多的时候，lua脚本文件显得尤为必要：


            3.lua语言编写的脚本
                测试代码：H_TestLua.testLuaFile()

        9.如何保证redis操作队列的原子性？
            a.redis事务
            b.redis+lua脚本操作
        10.内存回收策略：
            一般会采用allkeys-lru：当内存不足以容纳新写入数据时，在键空间中，移除最近最少使用的key
        11.复制
            读写分离、高可用；
            主从复制：
                一般情况下主服务负责写，从服务负责读，从而实现了读写分离，提高服务读写性能；

        12.JavaAPI、Spring 两个客户端使用哨兵模式访问redis-from-bk
            测试代码：App
        13.在spring缓存机制和redis结合
        测试项目：见包mybatis_spring_redis下面的文件：

            具体操作：
            1.配置数据源，测试代码mybatis_spring_redis.config下的ConfigDB；
            2.配置面向mapper接口编程，测试代码mybatis_spring_redis.config下的MapperConfig；
                注意：上面步骤1、步骤2是完全按照spring的注解的方式配置的；
            3.配置spring的缓存管理器：
                在spring项目中提供了缓存管理器接口：CacheManager，有了这个接口，各种各样的缓存
                就可以实现它来提供管理器的功能了，在spring-data-redis.jar包中实现CacheManager的接口是RedisCacheManager，
                因此要定义RedisCacheManager的Bean，不过在定义它之前需要定义RedisTemplate；
                    【当前spring-data-redis的版本是1.8.4.RELEASE，如果更高版本，不是这一的写法，此处要注意】
                具体操作文件：mybatis_spring_redis.config下的RedisConfig；
                //@EnableCaching 表示spring ioc容器启动缓存机制！！！！
            4.准备service、serviceImpl的类，测试代码mybatis_spring_redis.service.RoleServiceImpl
                @EnableCaching之后，spring 就可以允许通过下面常用的三个注解使用缓存了，具体用法如下：
                @Cacheable/@CachePut/@CacheEvict
                1.@Cacheable、 @CachePut 只能用在有返回值的方法上，而@CacheEvict可以用在无返回值的方法上，因为它不需要保存任何值；
                2.一般情况下，对于查询方法，用@Cacheable标注，修改、添加方法用@CachePut、删除方法用@CacheEvict；
                3.对于注解@Cacheable/@CachePut/@CacheEvict中的属性key、value：
                    value表示：是一个数组，可以引用多个缓存管理器；
                    key表示缓存中的键，它支持spring表达式，前面 笔记【5-bk-装配spring bean】 中介绍过spring表达式；
                4.spring表达式和缓存注解的约定，通过这些约定去引用方法的参数和返回值，使得其注入key所定义的spring表达式中，常用的spring表达式如下：
                    #result、#argument
                    对于#result：表示方法返回值，还可以通过spring表达式读取其属性值；
                    对于#argument：方法参数的名字，可以直接#参数名，也可以使用#p0或#a0的形式，0代表参数的索引
                    具体见service的代码
                5.注意:
                    @Cacheable，在查询数据时，如果缓存没有，则去数据库查询，然后再添加到缓存中；
                    如果查询结果为空，在添加redis缓存会出错，在@Cacheable中添加[unless="#result == null" 表示当结果为空的时候，不存入缓存]即可解决问题；
                    对于@CachePut来说，注意，操作持久化数据库之后的【返回值】很重要，具体见测试代码RoleServiceImpl中的更新方法：
                    对于@CacheEvict来说，value、key属性和注解@Cacheable、@CachePut 一个意思，allEntities表示删除删除缓存服务器中所有的缓存，key失效，慎用；
                        beforeInvocation属性默认false，表示执行完方法之后执行缓存删除，true表示先执行缓存删除，在执行方法，beforeInvocation名字说明了此处用了AOP动态代理技术；
                6.不适用缓存的情况:
                    命中率低的数据,不建议使用缓存,简单来说就是不被经常查询的数据,不用缓存
                7.自调用问题:
                    因为spring的缓存注解@Cacheable/@CachePut/@CacheEvict实现的功能也是通过AOP实现的，在spring事务中也遇到过类似的问题，就是在同一个类中调用其他有缓存注解的方法，
                    缓存功能失效；原因：自调用没有产生代理对象，这个和数据库事务是一个道理；见测试代码 RoleServiceImpl
        14.RedisTemplate对象下面操作流水线、事务和lua脚本实例
            见：测试代码：I_TestPipeTransLuaImpl
            总结：管道技术、或者事务技术，redis客户端的多个命令都是一次放入队列中的，把队列的命令一次性发送到redis服务，减少网络传输，提高数据处理的性能

            测试类：mybatis_spring_redis.config下的TestSpringRedisFrmSrc；

        * */
    }
}
