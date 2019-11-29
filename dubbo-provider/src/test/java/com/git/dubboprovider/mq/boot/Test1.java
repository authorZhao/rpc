package com.git.dubboprovider.mq.boot;

import com.git.dubboprovider.DubboProviderApplication;
import com.git.model.ApiResult;
import com.git.mq.service.ActivemqService;
import com.git.mq.service.BootMqService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Test1 {


    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private BootMqService bootMqService;

    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;

    @Test
    void test1() throws InterruptedException {
        for (int i = 1; ; i++) {
            jmsTemplate.convertAndSend(queue,"我系渣渣辉，第"+i+"次，发送消息");
            System.out.println("我系渣渣辉，第"+i+"次，发送消息");
            Thread.currentThread().sleep(30000);
        }

    }
    @Test
    void test2() throws InterruptedException {
        ApiResult apiResult = new ApiResult(1,"ceshji",null);
        Map<String, Object> map = new HashMap<>();

        map.put("queueName","queue1");
        map.put("param","我系渣渣辉");
        apiResult.setMap(map);

        for (int i = 1; ; i++) {
            bootMqService.sendStringQueue(apiResult);
            System.out.println("我系渣渣辉，第"+i+"次，发送消息");
            Thread.currentThread().sleep(30000);
        }

    }
}
