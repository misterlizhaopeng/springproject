package com.lp.advancedfeature.dlx;

import com.rabbitmq.client.*;

import java.io.IOException;

public class DlxConsumer extends DefaultConsumer {
    private Channel channel;
    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public DlxConsumer(Channel channel) {
        super(channel);
        this.channel=channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        //super.handleDelivery(consumerTag, envelope, properties, body);

        //消费端拒绝签收，并且不支持重回队列，那么该条消息就是一条死信消息
        channel.basicNack(envelope.getDeliveryTag(),false,false);
        //channel.basicAck(envelope.getDeliveryTag(), false);
    }
}
