package com.lp.advancedfeature.confirm_listener;

import com.rabbitmq.client.ConfirmListener;

import java.io.IOException;

public class LpConfirmListener implements ConfirmListener {
    /**
     * @param deliveryTag 唯一消息Id
     * @param multiple    是否批量
     * @throws IOException
     */
    @Override
    public void handleAck(long deliveryTag, boolean multiple) throws IOException {
        System.out.println("消息deliveryTag" + deliveryTag + "被正常签收.............");
    }

    @Override
    public void handleNack(long deliveryTag, boolean multiple) throws IOException {
        System.out.println("消息deliveryTag" + deliveryTag + "没被签收");
    }
}
