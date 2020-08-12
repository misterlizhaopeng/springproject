package com.lp.basic.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MessageProducer {
    public static void main(String[] args) throws Exception{
        //1:创建连接工厂
        ConnectionFactory connectionFactory=new ConnectionFactory();

        //2设置连接工厂的属性
        connectionFactory.setHost("192.168.25.140");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("consu_definition");
        connectionFactory.setUsername("lisi");
        connectionFactory.setPassword("lisi");

        //3:通过连接工厂创建连接对象
        Connection connection = connectionFactory.newConnection();

        //4:通过连接创建channel
        Channel channel = connection.createChannel();

        Map<String,Object> headsMap = new HashMap<>();
        headsMap.put("company","lp-Company");
        headsMap.put("name","lp-name");

        AMQP.BasicProperties build = new AMQP.BasicProperties().builder()
                .deliveryMode(2)//2 标识持久化消息  1 标识 服务重启后 消息不会被持久化
                .expiration("100000")//消息过期 10s
                .contentEncoding("utf-8")
                .correlationId(UUID.randomUUID().toString())
                .headers(headsMap)
                .build();

        //5:通过channel发送消息
        for(int i=0;i<5;i++) {
            String message = "hello,rabbitmq"+i;

            //channel.basicPublish("tuling.directchange","tuling.directchange.key",build,message.getBytes());
            channel.basicPublish("lp.directchange","lp.directchange.key",build,message.getBytes());
        }

        //6:关闭连接
        channel.close();
        connection.close();

    }
}
