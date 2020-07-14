package com.lp.advancedfeature.confirm_listener;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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



        //准备发送消息
        String exchangeName = "lp.confirm.topicexchange";
        String routingKey = "lp.confirm.key";

        //设置消息属性
        Map<String, Object> headderMap = new HashMap<>();
        headderMap.put("company", "lp");
        headderMap.put("location", "北京");

        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)
                .correlationId(UUID.randomUUID().toString())
                .timestamp(new Date())
                .headers(headderMap)
                .build();

        String msgContext = "你好 李朋....";

        //设置消息投递模式(确认模式)
        channel.confirmSelect();

        /**
         * 消息确认监听
         */
        channel.addConfirmListener(new LpConfirmListener());

//        for(int i=0;i<5;i++) {
//
//        }
        channel.basicPublish(exchangeName, routingKey, basicProperties, msgContext.getBytes());

        /**
         * 注意:在这里千万不能调用 channel.close ,不然 消费就不能接受确认了
         */
    }
}
