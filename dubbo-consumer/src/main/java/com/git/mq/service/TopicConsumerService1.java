package com.git.mq.service;

import com.git.model.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.ObjectMessage;
import java.util.List;

public class TopicConsumerService1 {

    private final  static Logger logger = LoggerFactory.getLogger(TopicConsumerService1.class);

    @JmsListener(destination = "stringTopic", containerFactory = "jmsListenerContainerTopic")
    public void receiveStringTopic(String msg) {
        System.out.println("ATopicConsumer接收到消息...." + msg);
    }


    @JmsListener(destination = "stringListTopic", containerFactory = "jmsListenerContainerTopic")
    public void receiveStringListTopic(List<String> list) {
        System.out.println("ATopicConsumer接收到集合主题消息...." + list);
    }


    @JmsListener(destination = "objTopic", containerFactory = "jmsListenerContainerTopic")
    public void receiveObjTopic(ObjectMessage objectMessage) throws Exception {
        System.out.println("ATopicConsumer接收到对象主题消息...." + objectMessage.getObject());
    }


    @JmsListener(destination = "objListTopic", containerFactory = "jmsListenerContainerTopic")
    public void receiveObjListTopic(ObjectMessage objectMessage) throws Exception {
        System.out.println("ATopicConsumer接收到的对象集合主题消息..." + objectMessage.getObject());
    }

    @JmsListener(destination = "topic", containerFactory = "jmsListenerContainerTopic")
    public void receiveObjListTopic(ApiResult apiResult) throws Exception {
        logger.info("====topic1===");
        System.out.println("接收到消息...." + apiResult);
    }

}