package com.lp.springwithrabbitmq.testpak.messagedelegate;

import com.lp.springwithrabbitmq.testpak.entity.Order;

import java.io.File;
import java.util.Map;

public class LpMsgDelegate {
    public void handleMessage(String msgBody) {
        System.out.println("LpMsgDelegate...handleMessage" + msgBody);
    }

    public void consumerMsg(String msg) {
        System.out.println("LpMsgDelegate...consumerMsg" + msg);
    }

    public void consumerTopicQueue(String msgBody) {
        System.out.println("LpMsgDelegate...consumerTopicQueue" + msgBody);

    }

    public void consumerTopicQueue2(String msgBody) {
        System.out.println("LpMsgDelegate...consumerTopicQueue2" + msgBody);

    }

    /**
     * 处理json
     *
     * @param jsonMap
     */
    public void consumerJsonMessage(Map jsonMap) {
        System.out.println("LpMsgDelegate ------------------>处理json" + jsonMap);
    }

    /**
     * 处理order得
     *
     * @param order
     */
    public void consumerJavaObjMessage(Order order) {
        System.out.println("LpMsgDelegate ------------------>处理java对象" + order.toString());

    }


    public void consumerFileMessage(File file) {
        System.out.println("LpMsgDelegate------------------>处理文件" + file.getName());
    }
}
