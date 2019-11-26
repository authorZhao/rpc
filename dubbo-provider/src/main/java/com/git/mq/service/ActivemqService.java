package com.git.mq.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class ActivemqService {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ActiveMQQueue activeMQQueue;
    /**
     * 向配置文件中的默认队列发送消息
     */
    public void sendMessage(final String msg) {
        System.out.println("向队列" + activeMQQueue.toString() + "发送了消息------------" + msg);
        jmsTemplate.send(activeMQQueue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
    /**
     * 向指定队列发送消息
     */
    public void sendMessage(final String msg,String queueName) {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(queueName);
        jmsTemplate.setDefaultDestination(activeMQQueue);
        System.out.println("向队列" + activeMQQueue.toString() + "发送了消息------------" + msg);
        jmsTemplate.send(activeMQQueue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

}
