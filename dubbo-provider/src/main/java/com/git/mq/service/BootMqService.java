package com.git.mq.service;

import com.git.model.ApiResult;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BootMqService {


   /* @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;*/

    @Autowired
    private JmsTemplate jmsTemplate;


    /**
     * 发送字符串消息队列
     *
     * @param apiResult 队列名称
     */
    public void sendStringQueue(ApiResult apiResult) {
        /*int queueType = (int)map.get("queueType");
        Object param = map.get("param");
        String beanName = (String)map.get("beanName");
        String methodName = (String)map.get("methodName");*/

        //this.jmsTemplate.convertAndSend(new ActiveMQQueue(queueName), message);
    }


    /**
     * 发送字符串集合消息队列
     *
     * @param queueName 队列名称
     * @param list      字符串集合
     */
    public void sendStringListQueue(String queueName, List<String> list) {
        this.jmsTemplate.convertAndSend(new ActiveMQQueue(queueName), list);
    }


    /**
     * 发送对象消息队列
     *
     * @param queueName 队列名称
     * @param obj       对象
     */
    public void sendObjQueue(String queueName, Serializable obj) {
        this.jmsTemplate.convertAndSend(new ActiveMQQueue(queueName), obj);
    }


    /**
     * 发送对象集合消息队列
     *
     * @param queueName 队列名称
     * @param objList   对象集合
     */
    public void sendObjListQueue(String queueName, List<Serializable> objList) {
        this.jmsTemplate.convertAndSend(new ActiveMQQueue(queueName), objList);
    }


    /**
     * 发送字符串消息主题
     *
     * @param topicName 主题名称
     * @param message   字符串
     */
    public void sendStringTopic(String topicName, String message) {
        this.jmsTemplate.convertAndSend(new ActiveMQTopic(topicName), message);
    }


    /**
     * 发送字符串集合消息主题
     *
     * @param topicName 主题名称
     * @param list      字符串集合
     */
    public void sendStringListTopic(String topicName, List<String> list) {
        this.jmsTemplate.convertAndSend(new ActiveMQTopic(topicName), list);
    }


    /**
     * 发送对象消息主题
     *
     * @param topicName 主题名称
     * @param obj       对象
     */
    public void sendObjTopic(String topicName, Serializable obj) {
        this.jmsTemplate.convertAndSend(new ActiveMQTopic(topicName), obj);
    }


    /**
     * 发送对象集合消息主题
     *
     * @param topicName 主题名称
     * @param objList   对象集合
     */
    public void sendObjListTopic(String topicName, List<Serializable> objList) {
        this.jmsTemplate.convertAndSend(new ActiveMQTopic(topicName), objList);
    }

    public void sendTopic(ApiResult apiResult){
        sendDescion(new ActiveMQTopic(),apiResult);
    }

    public void sendQueue(ApiResult apiResult){
        sendDescion(new ActiveMQQueue(),apiResult);
    }

    private void sendDescion(Destination destination,ApiResult apiResult){
        Map<String, Object> map = apiResult.getMap();
        String queueName = (String)map.get("queueName");
        if(queueName==null||queueName.equals("")){
            throw new RuntimeException("队列名称不可为空");
        }
        if(destination instanceof ActiveMQQueue){
            ((ActiveMQQueue)destination).setPhysicalName(queueName);
        }

        if(destination instanceof ActiveMQTopic){
            ((ActiveMQTopic)destination).setPhysicalName(queueName);
        }
        jmsTemplate.convertAndSend(destination,apiResult);
    }
}