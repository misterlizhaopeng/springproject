package jedis_redisTemplate;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;

public class E_SubscribePublish {

    //按照指定的渠道，发布消息，此例渠道：chat
    @Test
    public void publish() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);

        redisTemplate.convertAndSend("chat", "hello");
    }

    //模拟订阅消息，启动spring容器，开始监视订阅的消息
    @Test
    public void subscribeChannel(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:redisTemplate-subscribe.xml");
        System.out.println("Manager provider starting ....");
        // System.in.read();表示停止程序，让spring容器一直运行着，如果没有此行代码，spring 容器会立刻关闭；
        // 还有一个方法：while(true){Thread.sleep(1000);};睡一秒执行一次，这样cpu不至于利用很高！！！
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
