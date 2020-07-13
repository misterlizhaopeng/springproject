package com.lp.basic.fanoutexchange;

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

        //创建连接
        Connection connection = connectionFactory.newConnection();

        //创建channel
        Channel channel = connection.createChannel();

        //声明交换机
        String exchangeName = "lp.fanoutexchange";
        String exchangeType = "fanout";
        channel.exchangeDeclare(exchangeName, exchangeType, true, true, null);

        //声明队列
        String quequName = "lp.fanout.queue";
        channel.queueDeclare(quequName, true, false, false, null);

        //声明绑定关系,fanout不需要routingKey的等值dieect或者匹配topic，只要队列和交换机绑定就可以实现：交换机exchange上面的接受的数据全部都会
        //发送给队列；
        String routingKey = "";
        channel.queueBind(quequName, exchangeName, routingKey);

        //声明一个消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        //开始消费
        /**
         * 开始消费
         */
        channel.basicConsume(quequName, true, queueingConsumer);

        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            System.out.println("接受到消息:" + new String(delivery.getBody()));
        }
    }
}
