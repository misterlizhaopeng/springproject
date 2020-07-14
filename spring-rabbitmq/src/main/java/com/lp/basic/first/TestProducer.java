package com.lp.basic.first;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TestProducer {
    public static void main(String[] args) throws Exception{
        //创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.25.140");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("consu_definition");
        connectionFactory.setUsername("lisi");
        connectionFactory.setPassword("lisi");
        connectionFactory.setConnectionTimeout(100000);


        //创建连接
        Connection connection = connectionFactory.newConnection();

        //创建我们的channle
        Channel channel = connection.createChannel();

        for (int i = 0; i < 5; i++) {
            String message = "hello" + i;
            /**
             * 老师以前讲过说我们的消息会发送的exchange上，
             * 但是在这里我们没有指定交换机? 那我们的消息发送到哪里了？？？？
             * The default exchange is implicitly bound to every queue, with a routing key equal to the queue name.
             * It is not possible to explicitly bind to, or unbind from the default exchange. It also cannot be deleted.
             *
             * 说明:假如我们消息发送的时候没有指定具体的交换机的话，那么就会发送到rabbimtq 指定默认的交换机上，
             * 那么该交换机就会去根据 routing_key 查找对应的 queueName 然后发送的该队列上.
             *
             * 也可以这么理解：每一个虚拟主机上都会存在这种情况；
             */
            channel.basicPublish("", "lp-queue-011", null, message.getBytes());
        }
        //关闭连接
        channel.close();
        connection.close();
    }
}
