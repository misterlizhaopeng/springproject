package bean;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;


/**
 * channel 为chat2
 */
public class MyRedisMessageListener2 implements MessageListener {
    private RedisTemplate redisTemplate;

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        // 获取channel
        byte[] channel = message.getChannel();
        String channelStr = (String) getRedisTemplate().getStringSerializer().deserialize(channel);

        // 获取消息
        byte[] body = message.getBody();
        String messageObj = (String) getRedisTemplate().getValueSerializer().deserialize(body);

        //当channel为chat2的时候
        if ("chat2".equalsIgnoreCase(channelStr)) {
            System.out.println("chat2:\r\n"+messageObj);

        }
      /*



        System.err.println(channelStr);
        // 渠道名称转换
        String bytesStr = new String(bytes);
        System.err.println(bytesStr);



        */
    }
}
