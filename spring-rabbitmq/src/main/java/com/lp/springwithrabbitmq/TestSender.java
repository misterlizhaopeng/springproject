package com.lp.springwithrabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lp.springwithrabbitmq.testpak.entity.Order;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

public class TestSender {

    private RabbitAdmin rabbitAdmin;
    private RabbitTemplate rabbitTemplate;

    @Before
    public void before() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RabbitmqConfig.class);
        rabbitTemplate = ctx.getBean(RabbitTemplate.class);
        rabbitAdmin = ctx.getBean(RabbitAdmin.class);
    }

    @Test
    public void testTopicExchange() {
        //声明一个交换机
        TopicExchange topicExchange = new TopicExchange("rabbitadmin.topic.exchange", true, false);
        rabbitAdmin.declareExchange(topicExchange);

        //申明一个队列
        Queue queue = new Queue("rabbitadmin.topic.queue", true);
        rabbitAdmin.declareQueue(queue);

        //申明一个绑定
        Binding binding = new Binding("rabbitadmin.topic.queue", Binding.DestinationType.QUEUE,
                "rabbitadmin.topic.exchange", "rabbitadmin.#", null);
        rabbitAdmin.declareBinding(binding);
    }

    @Test
    public void testDirectExchange() {
        DirectExchange directExchange = new DirectExchange("rabbitadmin.direct.exchange", true, false);
        rabbitAdmin.declareExchange(directExchange);
        Queue queue = new Queue("rabbitadmin.direct.queue", true);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with("rabbitadmin.key.#"));

    }


    //向队列 testDirectQueue 发送消息
    @Test
    public void testRabbitmqTemplate() {

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().put("company", "lp");
        messageProperties.getHeaders().put("name", "lpmsg");

        String msgBody = "hello lp";
        Message message = new Message(msgBody.getBytes(), messageProperties);

        //rabbitTemplate.send("lp.topic.exchange","topic.haha",message);

        //不需要message对象发送
        rabbitTemplate.convertAndSend("lp.direct.exchange", "direct.key", "lpmsg");
    }
    //向队列 testTopicQueue2  发送消息
    @Test
    public void simpleMessageListenerContainerTest() {
        rabbitTemplate.convertAndSend("lp.topic.exchange", "topic.xixi", "你好 lp");
    }


    @Test
    public void messageListenerAdaperQueueOrTagToMethodName() {
        rabbitTemplate.convertAndSend("lp.topic.exchange", "topic.xixi", "你好 lp");
        rabbitTemplate.convertAndSend("lp.topic.exchange", "topic.key.xixi", "你好 lpmsg");
    }

    @Test
    public void sendJson() throws JsonProcessingException {

        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        order.setCreateDt(new Date());
        order.setPayMoney(10000.00);
        order.setUserName("lpmsg");

        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = objectMapper.writeValueAsString(order);

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message orderMsg = new Message(orderJson.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("lp.direct.exchange", "rabbitmq.order", orderMsg);

    }

    @Test
    public void sendJavaObj() throws JsonProcessingException {

        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        order.setCreateDt(new Date());
        order.setPayMoney(10000.00);
        order.setUserName("lpmsg");

        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = objectMapper.writeValueAsString(order);

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        messageProperties.getHeaders().put("__TypeId__", "com.lp.entity.Order");
        Message orderMsg = new Message(orderJson.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("lp.direct.exchange", "rabbitmq.order", orderMsg);

    }


    @Test
    public void sendImage() throws IOException {
        byte[] imgBody = Files.readAllBytes(Paths.get("D:/lpmsg/file01", "lpmsg.png"));
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("img/png");
        Message message = new Message(imgBody, messageProperties);
        rabbitTemplate.send("lp.direct.exchange", "rabbitmq.file", message);

    }

    @Test
    public void sendWord() throws IOException {
        byte[] imgBody = Files.readAllBytes(Paths.get("D:/lpmsg/file01", "spring.docx"));
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/word");
        Message message = new Message(imgBody, messageProperties);
        rabbitTemplate.send("lp.direct.exchange", "rabbitmq.file", message);

    }
}
