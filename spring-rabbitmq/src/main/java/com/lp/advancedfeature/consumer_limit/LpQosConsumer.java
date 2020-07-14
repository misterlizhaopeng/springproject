package com.lp.advancedfeature.consumer_limit;

import com.rabbitmq.client.*;

import java.io.IOException;

public class LpQosConsumer extends DefaultConsumer {
    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public LpQosConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    private Channel channel;

    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("consumerTag:" + consumerTag);
        System.out.println("envelope:" + envelope);
        System.out.println("properties:" + properties);
        System.out.println("body:" + new String(body));

        // 消费端的手动签收,假如关闭手动签收，也关闭自动签收，那么消费端只会接收到一条消息
        //if (new String(body).equals("你好lp:0"))
            //channel.basicAck(envelope.getDeliveryTag(), false);  // 手动确认消息
        /**
         * multiple:false 标识不批量签收
         */
        //channel.basicAck(envelope.getDeliveryTag(),false);

    }
}
