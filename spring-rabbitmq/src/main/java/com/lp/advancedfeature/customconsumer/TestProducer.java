package com.lp.advancedfeature.customconsumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TestProducer {
    public static void main(String[] args) throws Exception {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.25.140");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("consu_definition");
        connectionFactory.setUsername("lisi");
        connectionFactory.setPassword("lisi");

        //创建一个连接
        Connection connection = connectionFactory.newConnection();

        //创建一个channel
        Channel channel = connection.createChannel();

        //定义交换机的名称
        String exchangeName = "lp.customconsumer.direct";

        String routingKey = "lp.customconsumer.key";

        String msgBody = "你好lp";
        for (int i = 0; i < 5; i++) {
            channel.basicPublish(exchangeName, routingKey, null, (msgBody+":" + i).getBytes());
        }
    }
}
