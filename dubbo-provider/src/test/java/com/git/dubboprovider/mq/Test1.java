package com.git.dubboprovider.mq;

import com.git.mq.service.ActivemqService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 获取接口实现类测试
 */
public class Test1 {

    private ActivemqService activemqService;

    @Test
    public void getCjildClassByInteface() throws ClassNotFoundException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-activemq.xml");
        context.start();
        activemqService = context.getBean(ActivemqService.class);
        System.out.println("服务已经启动");
        int i = 0;
        while(true){

            activemqService.sendMessage("哈哈,第"+i+"次发送消息");

           try {
                Thread.sleep(3000*5);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }




}
