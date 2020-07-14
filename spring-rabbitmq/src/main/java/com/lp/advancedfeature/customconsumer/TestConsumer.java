package com.lp.advancedfeature.customconsumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TestConsumer {
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

        //声明交换机
        String exchangeName = "lp.customconsumer.direct";
        String exchangeType = "direct";
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,null);

        //声明队列
        String queueName = "lp.customconsumer.queue";
        channel.queueDeclare(queueName,true,false,false,null);

        //交换机绑定队列
        String routingKey = "lp.customconsumer.key";
        channel.queueBind(queueName,exchangeName,routingKey);

        channel.basicConsume(queueName,new LpConsumer(channel));
    }
}
