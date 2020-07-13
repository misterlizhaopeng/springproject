package com.lp.basic.first;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class TestConsumer {
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

        //声明队列名称
        String  queueName = "lp-queue-01";

        channel.queueDeclare(queueName,true,false,false,null);

        //创建我们的消费者
        QueueingConsumer queueingConsumer =new QueueingConsumer(channel);

        channel.basicConsume(queueName,true,queueingConsumer);

        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            System.out.println("消费消息:"+new String(delivery.getBody()));

        }
    }
}
