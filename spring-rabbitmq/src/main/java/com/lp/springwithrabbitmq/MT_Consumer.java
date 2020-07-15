package com.lp.springwithrabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//测试
public class MT_Consumer {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RabbitmqConfig.class);
        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}
