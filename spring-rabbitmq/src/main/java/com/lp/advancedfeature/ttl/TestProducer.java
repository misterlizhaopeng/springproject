package com.lp.advancedfeature.ttl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;


// 设置队列的消息长度以及过期时间
public class TestProducer {
    public static void main(String[] args) throws Exception{
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.25.140");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("consu_definition");
        connectionFactory.setUsername("lisi");
        connectionFactory.setPassword("lisi");
        connectionFactory.setConnectionTimeout(100000);

        //创建一个连接
        Connection connection = connectionFactory.newConnection();

        //创建一个channel
        Channel channel = connection.createChannel();

        //定义交换机的名称
        String exchangeName = "lp.ttl.direct";

        String routingKey = "lp.ttl.key";

        String queueName = "lp.ttl.queue";

        //申明交换机
        channel.exchangeDeclare(exchangeName,"direct",true,false,null);

        //申明队列
        Map<String,Object> queueArgs = new HashMap<>();
        //设置队列中的消息10s没有被消费就会过期
        queueArgs.put("x-message-ttl",10000);
        //队列的长度
        queueArgs.put("x-max-length",4);
        channel.queueDeclare(queueName,true,false,false,queueArgs);

        //绑定
        channel.queueBind(queueName,exchangeName,routingKey);

        String msgBody = "你好lp";
        for(int i=0;i<10;i++) {
            channel.basicPublish(exchangeName,routingKey,null,(msgBody+i).getBytes());
        }
    }
}
