package com.git.mq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.ObjectMessage;
import java.util.List;

public class BootMqQueneConsumerService2 {

    private final  static Logger logger = LoggerFactory.getLogger(BootMqQueneConsumerService2.class);

    @JmsListener(destination = "queue")
    public void receiveStringQueue(String msg) {
        logger.info("收到消息，我系消费者2");
        System.out.println("接收到消息...." + msg);
    }


    @JmsListener(destination = "stringListQueue")
    public void receiveStringListQueue(List<String> list) {
        System.out.println("接收到集合队列消息...." + list);
    }


    @JmsListener(destination = "objQueue")
    public void receiveObjQueue(ObjectMessage objectMessage) throws Exception {
        System.out.println("接收到对象队列消息...." + objectMessage.getObject());
    }


    @JmsListener(destination = "objListQueue")
    public void receiveObjListQueue(ObjectMessage objectMessage) throws Exception {
        System.out.println("接收到的对象队列消息..." + objectMessage.getObject());
    }


}