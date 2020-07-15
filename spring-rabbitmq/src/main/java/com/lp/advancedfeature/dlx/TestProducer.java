package com.lp.advancedfeature.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

//启动生产者，
public class TestProducer {
    public static void main(String[] args) throws Exception{
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

        //消息十秒没有被消费，那么就会转到死信队列上
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)
                .expiration("10000")
                .build();

        //声明正常的队列
        String nomalExchangeName = "lp.nomaldlx.exchange";
        String routingKey = "lp.dlx.key1";

        String message = "我是测试的死信消息";
        for(int i=0;i<100;i++) {

            channel.basicPublish(nomalExchangeName,routingKey,basicProperties,message.getBytes());
        }
    }
}
