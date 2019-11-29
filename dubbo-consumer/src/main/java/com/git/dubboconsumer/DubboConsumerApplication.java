package com.git.dubboconsumer;

import com.git.mq.service.BootMqQueneConsumerService;
import com.git.mq.service.BootMqQueneConsumerService2;
import com.git.mq.service.TopicConsumerService1;
import com.git.mq.service.TopicConsumerService2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@Import(value={BootMqQueneConsumerService.class, BootMqQueneConsumerService2.class, TopicConsumerService1.class, TopicConsumerService2.class})
@EnableJms
public class DubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }

    //需要给topic定义独立的JmsListenerContainer
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }
}
