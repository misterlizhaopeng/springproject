package com.lp.advancedfeature.returnlistener;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TestProducer {
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

        //准备发送消息
        String exchangeName = "lp.retrun.direct";
        String okRoutingKey = "lp.retrun.key.ok";
        String errorRoutingKey = "lp.retrun.key.error";

        /**
         * 设置监听不可达消息
         */
        channel.addReturnListener(new LpRetrunListener());


        //设置消息属性
        Map<String,Object> lpInfo = new HashMap<>();
        lpInfo.put("company","lp");
        lpInfo.put("location","北京");

        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)
                .correlationId(UUID.randomUUID().toString())
                .timestamp(new Date())
                .headers(lpInfo)
                .build();


        String msgContext = "你好 李朋...."+System.currentTimeMillis();
        /**
         * 发送消息
         * mandatory:该属性设置为false,那么不可达消息就会被 mq broker 给删除掉；
         *          如果为true,那么mq会调用我们得retrunListener 来告诉我们业务系统 说该消息
         *          不能成功发送.
         */
        channel.basicPublish(exchangeName,okRoutingKey,true,basicProperties,msgContext.getBytes());


        String errorMsg1 = "你好 李朋 mandotory为false...."+System.currentTimeMillis();

        //错误发送   mandotory为false
        channel.basicPublish(exchangeName,errorRoutingKey,false,basicProperties,errorMsg1.getBytes());

        String errorMsg2 = "你好 李朋 mandotory为true...."+System.currentTimeMillis();

        //错误发送 mandotory 为true
        channel.basicPublish(exchangeName,errorRoutingKey,true,basicProperties,errorMsg2.getBytes());

    }
}
