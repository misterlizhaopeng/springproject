package com.lp.advancedfeature.dlx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

public class TestConsumer {
    public static void main(String[] args) throws Exception {
        //设置连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.25.140");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("consu_definition");
        connectionFactory.setUsername("lisi");
        connectionFactory.setPassword("lisi");
        connectionFactory.setConnectionTimeout(100000);

        //获取连接
        Connection connection = connectionFactory.newConnection();

        //获取一个channel
        Channel channel = connection.createChannel();

        //声明正常的队列
        String nomalExchangeName = "lp.nomaldlx.exchange";
        String exchangeType = "topic";
        String nomalqueueName = "lp.nomaldex.queue";
        String routingKey = "lp.dlx.#";

        //申明死信队列
        String dlxExhcangeName = "lp.dlx.exchange";
        String dlxQueueName = "lp.dlx.queue";

        channel.exchangeDeclare(nomalExchangeName, exchangeType, true, false, null);


        Map<String, Object> queueArgs = new HashMap<>();
        //在正常队列上绑定死信队列
        queueArgs.put("x-dead-letter-exchange", dlxExhcangeName);
        queueArgs.put("x-max-length", 5);// 设置正常队列的消息长度为5（假如一共发送了100条，那么95条先转到私信队列上）,生产端设置了消息10s不消费，自动转到到死信队列中去；
        channel.queueDeclare(nomalqueueName, true, false, false, queueArgs);
        channel.queueBind(nomalqueueName, nomalExchangeName, routingKey);


        //声明死信队列
        channel.exchangeDeclare(dlxExhcangeName, exchangeType, true, false, null);
        channel.queueDeclare(dlxQueueName, true, false, false, null);
        channel.queueBind(dlxQueueName, dlxExhcangeName, "#");

        channel.basicConsume(nomalqueueName, false, new DlxConsumer(channel));
    }
}
