package com.git.dubboprovider.mq.boot;

import com.git.dubboprovider.DubboProviderApplication;
import com.git.mq.service.ActivemqService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = DubboProviderApplication.class)
@RunWith(SpringRunner.class)
class Test1 {


    @Autowired
    private ActivemqService activemqService;

    @Test
    void test1() throws InterruptedException {
        for (int i = 1; ; i++) {
            activemqService.sendMessage("springboot哈哈,第"+i+"次发送消息");
            Thread.currentThread().sleep(5000);
        }

    }

}
