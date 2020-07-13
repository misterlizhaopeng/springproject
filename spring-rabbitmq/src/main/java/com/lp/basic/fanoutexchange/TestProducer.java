package com.lp.basic.fanoutexchange;

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

        //创建连接
        Connection connection = connectionFactory.newConnection();

        //创建channel
        Channel channel = connection.createChannel();

        //定义交换机名称
        String exchangeName = "lp.fanoutexchange";

        //定义routingKey
        String routingKey = "";

        //消息体内容
        String messageBody = "hello lp ";
        channel.basicPublish(exchangeName, "", null, "我是第一条消息".getBytes());
        channel.basicPublish(exchangeName, "", null, "我是第二条消息".getBytes());
        channel.basicPublish(exchangeName, "", null, "我是第三条消息".getBytes());
    }
}
