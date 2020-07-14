package com.lp.advancedfeature.ack_nack;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TestConsumer {
    public static void main(String[] args) throws Exception {
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
        String exchangeName = "lp.ack.direct";
        String exchangeType = "direct";
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, null);

        //声明队列
        String queueName = "lp.ack.queue";
        channel.queueDeclare(queueName, true, false, false, null);

        //交换机绑定队列
        String routingKey = "lp.ack.key";
        channel.queueBind(queueName, exchangeName, routingKey);

        /**
         * 消费端限流 需要关闭消息自动签收
         */
        channel.basicConsume(queueName, false, new LpAckConsumer(channel));
    }
}
