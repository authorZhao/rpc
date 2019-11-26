package com.git.mq.config;

import com.git.config.condition.MqCondition;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class ActivemqConfig {

    @Conditional(MqCondition.class)
    @Bean
    public Queue queue() {
    return new ActiveMQQueue("active.queue");
    }
}
