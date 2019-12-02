package com.git.dubboconsumer.mq;

import com.git.mq.service.ActiveService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.TextMessage;

public class Test2 {

    @Test
    public void test1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-activemq.xml");

        // 从spring容器中取对象
        ActiveService activeService = context.getBean(ActiveService.class);

        System.out.println("start consumer");
        while (true) {
            try {
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }





}
