package com.lp.advancedfeature.ack_nack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        String exchangeName = "lp.ack.direct";

        String routingKey = "lp.ack.key";

        String msgBody = "你好lp";


        for(int i=0;i<10;i++) {

            Map<String,Object> infoMap = new HashMap<>();
            infoMap.put("mark",i);

            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder()
                    .deliveryMode(2)//消息持久化
                    .contentEncoding("UTF-8")
                    .correlationId(UUID.randomUUID().toString())
                    .headers(infoMap)
                    .build();
            channel.basicPublish(exchangeName,routingKey,basicProperties,(msgBody+"------------"+i).getBytes());
        }
    }
}
