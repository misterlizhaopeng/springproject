package com.lp.advancedfeature.ack_nack;

import com.rabbitmq.client.*;

import java.io.IOException;

public class LpAckConsumer extends DefaultConsumer {

    private Channel channel;
    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public LpAckConsumer(Channel channel) {
        super(channel);
        this.channel=channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        //super.handleDelivery(consumerTag, envelope, properties, body);

        try{
            //模拟业务
            Integer mark = (Integer) properties.getHeaders().get("mark");
            if(mark != 0 ) {
                System.out.println("消费消息:"+new String(body));
                // 此处为手动确认签收消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }else{
                throw new RuntimeException("模拟业务异常");
            }
        }catch (Exception e) {
            System.out.println("异常消费消息:"+new String(body));
            // basicNack表示不签收,,
            //重回队列  ,这样会出现死循环，所以真是开发中，一般不会重回队列
            //channel.basicNack(envelope.getDeliveryTag(),false,true);
            //不重回队列，这种情况既不签收，也不重回队列；
            channel.basicNack(envelope.getDeliveryTag(),false,false);

        }
    }
}
