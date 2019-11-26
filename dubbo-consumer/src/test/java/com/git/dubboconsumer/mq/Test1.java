package com.git.dubboconsumer.mq;

import com.git.mq.service.ActiveService;
import com.git.service.CartService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.TextMessage;

public class Test1 {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-activemq.xml");

        // 从spring容器中取对象
        ActiveService activeService = context.getBean(ActiveService.class);

        System.out.println("start consumer");
        while (true) {
            try {
            //调用服务
                //System.out.printf("", new CartSesriveImpl().findCartByUserId(8l));
                TextMessage receive = activeService.receive();
                System.out.println(receive);
                Thread.currentThread().sleep(5000);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }





}
