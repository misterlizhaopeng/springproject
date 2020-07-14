package com.lp.advancedfeature.confirm_listener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

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
        String exchangeName = "lp.confirm.topicexchange";
        String exchangeType = "topic";
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, null);

        //声明队列
        String queueName = "lp.confirm.queue";
        channel.queueDeclare(queueName, true, false, false, null);

        //交换机绑定队列
        String routingKey = "lp.confirm.#";
        channel.queueBind(queueName, exchangeName, routingKey);

        //创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        //消费消息
        channel.basicConsume(queueName, true, queueingConsumer);

        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            System.out.println("消费端在:" + System.currentTimeMillis() + "消费:" + new String(delivery.getBody()));
            System.out.println("correlationId:" + delivery.getProperties().getCorrelationId());
        }
    }
}
