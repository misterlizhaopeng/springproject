package jedis_redisTemplate;

import bean.Role;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import redis.clients.jedis.Jedis;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class H_TestLua {

    /*
        简单字符串的存储：redis-cli下直接，执行输入lua代码，缓存脚本形式

    */
    @Test
    public void testLuaScript() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("redisTemplate.xml");
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);


        // 如果是简单的操作，使用原来的Jedis会简易些
        Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        // 执行简单的脚本
        String helloLua = (String) jedis.eval("return 'hello lua'");
        System.out.println(helloLua);

        // 执行带参数的脚本
        jedis.eval("redis.call('set',KEYS[1], ARGV[1])", 1, "lua-key", "lua-valuex");
        System.out.println((String) jedis.get("lua-key"));

        // 缓存脚本，返回sha1签名标识
        String shaCode = jedis.scriptLoad("redis.call('set',KEYS[1], ARGV[1])");
        // 通过标识执行脚本
        jedis.evalsha(shaCode, 1, "sha-key", "sha-value");

        // 关闭连接
        jedis.close();
    }

    /*
          通过spring提供的RedisScript接口（实现类DefaultRedisScript）对象执行lua脚本操作对象类型的数据；

      */
    @Test
    public void testLuaObject() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("redisTemplate.xml");
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);

        // 定义默认脚本封装类
        DefaultRedisScript<Role> redisScript = new DefaultRedisScript<Role>();
        // 设置脚本
        redisScript.setScriptText("redis.call('set', KEYS[1], ARGV[1])  return redis.call('get', KEYS[1])");

        // 定义操作的key列表
        List<String> keyList = new ArrayList<String>();
        keyList.add("role12");

        // 需要序列化保存和读取的对象
        Role role = new Role();
        role.setId(21);
        role.setName("abc");
        role.setNote("note-content");

        // 获得标识字符串
        String sha1 = redisScript.getSha1();
        System.out.println(sha1);

        // 设置返回结果类型，如果没有这句话，结果返回为空
        redisScript.setResultType(Role.class);


        // 定义序列化器
        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();


        // 执行脚本
        // 第一个是RedisScript接口对象，第二个是参数序列化器
        // 第三个是结果序列化器，第四个是Reids的key列表，最后是参数列表
        Role obj = (Role) redisTemplate.execute(redisScript, serializer, serializer, keyList, role);


        // 打印结果
        System.out.println(obj);
    }


    /*

        执行lua脚本文件-当lua脚本逻辑比较多的时候，lua脚本文件显得尤为必要：

    */
    @Test
    public void testLuaFile() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("redisTemplate.xml");
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);

        // 读入文件流
        File file = new File("C:\\Users\\Administrator\\Desktop\\del\\test.lua");

        byte[] bytes = getFileToByte(file);
        Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();

        // 发送文件二进制给Redis，这样REdis就会返回sha1标识
        byte[] sha1 = jedis.scriptLoad(bytes);

        // 使用返回的标识执行，其中第二个参数2，表示使用2个键
        // 而后面的字符串都转化为了二进制字节进行传输
        Object obj = jedis.evalsha(sha1, 2, "key1".getBytes(), "key2".getBytes(), "2".getBytes(), "4".getBytes());

        System.out.println(obj);

    }


    /**
     * 把文件转化为二进制数组
     *
     * @param file 文件
     * @return 二进制数组
     */
    public static byte[] getFileToByte(File file) {
        byte[] by = new byte[(int) file.length()];
        try {
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            byte[] bb = new byte[2048];
            int ch;
            ch = is.read(bb);
            while (ch != -1) {
                bytestream.write(bb, 0, ch);
                ch = is.read(bb);
            }
            by = bytestream.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return by;
    }
}
