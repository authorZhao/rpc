/*
package com.git.mq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.connection.SingleConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;*//**

public class MessageConsumerRegister {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumerRegister.class);

    private static SingleConnectionFactory connectionFactory;

    private static ArrayList<ConsumerPojo> consumerRegistes = new ArrayList();
    private static final int REGISTER_DELAY_SECOND_TIME = 10;

    public MessageConsumerRegister() {
    }

    public static void registerAll(ApplicationContext appContext) {
        delayRegisterAll(appContext, 10);
    }

    public static void unregisterAll() {
        Iterator var0 = consumerRegistes.iterator();

        while(var0.hasNext()) {
            MessageConsumerRegister.ConsumerPojo consumerPojo = (MessageConsumerRegister.ConsumerPojo)var0.next();

            try {
                consumerPojo.session.close();
                consumerPojo.conn.stop();
            } catch (JMSException var3) {
                var3.printStackTrace();
            }
        }

        consumerRegistes.clear();
    }

    private static void delayRegisterAll(final ApplicationContext appContext, final int delaySecondTime) {
        (new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep((long)delaySecondTime * 1000L);
                } catch (InterruptedException var2) {
                }

                MessageConsumerRegister.registerAllImp(appContext);
            }
        })).start();
    }

    private static void registerAllImp(ApplicationContext appContext) {
        LOGGER.info("初始化消息队列:开始");
        connectionFactory = (SingleConnectionFactory)appContext.getBean(SingleConnectionFactory.class);

        try {
            Map<String, QueueMessageConsumer> queueConsumers = appContext.getBeansOfType(QueueMessageConsumer.class);
            Iterator var2 = queueConsumers.keySet().iterator();

            while(var2.hasNext()) {
                String queueBeanName = (String)var2.next();
                QueueName anQueueName = (QueueName)AnnotationUtil.getAnnotation(queueConsumers.get(queueBeanName), QueueName.class);
                if (anQueueName != null) {
                    consumerRegistes.add(RegisterQueue(anQueueName.value(), (QueueMessageConsumer)queueConsumers.get(queueBeanName)));
                    LOGGER.info("注册Queue监听：" + anQueueName.value());
                }
            }

            Map<String, TopicMessageConsumer> topicConsumers = appContext.getBeansOfType(TopicMessageConsumer.class);
            Iterator var8 = topicConsumers.keySet().iterator();

            while(var8.hasNext()) {
                String topicBeanName = (String)var8.next();
                QueueName anTopicName = (QueueName)AnnotationUtil.getAnnotation(topicConsumers.get(topicBeanName), QueueName.class);
                if (anTopicName != null) {
                    consumerRegistes.add(RegisterTopic(anTopicName.value(), (TopicMessageConsumer)topicConsumers.get(topicBeanName)));
                    LOGGER.info("注册Topic监听：" + anTopicName.value());
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        LOGGER.info("初始化消息队列:完毕");
    }

    private static MessageConsumerRegister.ConsumerPojo RegisterQueue(String queueName, QueueMessageConsumer qmConsumer) throws JMSException {
        MessageConsumerRegister.ConsumerPojo cp = new MessageConsumerRegister.ConsumerPojo();
        cp.conn = connectionFactory.createConnection();
        cp.session = cp.conn.createSession(false, 1);
        cp.dest = cp.session.createQueue(queueName);
        cp.consumer = cp.session.createConsumer(cp.dest);
        cp.conn.start();
        cp.consumer.setMessageListener(new MessageConsumerRegister.QueueMessageReciver(qmConsumer));
        return cp;
    }

    private static MessageConsumerRegister.ConsumerPojo RegisterTopic(String topicName, TopicMessageConsumer qmConsumer) throws JMSException {
        MessageConsumerRegister.ConsumerPojo cp = new MessageConsumerRegister.ConsumerPojo();
        cp.conn = connectionFactory.createConnection();
        cp.session = cp.conn.createSession(false, 1);
        cp.dest = cp.session.createTopic(topicName);
        cp.consumer = cp.session.createConsumer(cp.dest);
        cp.conn.start();
        cp.consumer.setMessageListener(new MessageConsumerRegister.TopicMessageReciver(qmConsumer));
        return cp;
    }

    private static class ConsumerPojo {
        public Connection conn;
        public Session session;
        public Destination dest;
        public MessageConsumer consumer;

        private ConsumerPojo() {
        }
    }

    private static class TopicMessageReciver implements MessageListener {
        private TopicMessageConsumer topicConsumer;

        public TopicMessageReciver(TopicMessageConsumer topicConsumer) {
            this.topicConsumer = topicConsumer;
        }

        public void onMessage(Message message) {
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage)message;

                try {
                    String strMsg = msg.getText();
                    MqMsgDto msgDto = (MqMsgDto)JsonUtil.json2Obj(strMsg, MqMsgDto.class);
                    if (msgDto == null) {
                        MessageConsumerRegister.LOGGER.error("消息队列数据转换异常，原内容：" + strMsg);
                    }

                    this.topicConsumer.onTopic(msgDto);
                } catch (JMSException var5) {
                    MessageConsumerRegister.LOGGER.error(var5.toString());
                }
            }

        }
    }

    private static class QueueMessageReciver implements MessageListener {
        private QueueMessageConsumer queueConsumer;

        public QueueMessageReciver(QueueMessageConsumer queueConsumer) {
            this.queueConsumer = queueConsumer;
        }

        public void onMessage(Message message) {
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage)message;
                String strMsg = "";

                try {
                    strMsg = msg.getText();
                    MqMsgDto msgDto = (MqMsgDto)JsonUtil.json2Obj(strMsg, MqMsgDto.class);
                    if (msgDto == null) {
                        MessageConsumerRegister.LOGGER.error("消息队列数据转换异常，原内容：" + strMsg);
                    }

                    this.queueConsumer.onMessage(msgDto);
                } catch (JMSException var5) {
                    MessageConsumerRegister.LOGGER.error(var5.toString());
                } catch (RuntimeException var6) {
                    throw var6;
                }
            }

        }
    }
}*/
