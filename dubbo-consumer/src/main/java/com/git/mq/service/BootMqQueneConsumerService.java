package com.git.mq.service;

import com.alibaba.fastjson.JSON;
import com.git.model.ApiResult;
import com.git.model.User;
import com.git.service.CartService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.ObjectMessage;
import java.util.List;
import java.util.Map;

public class BootMqQueneConsumerService {
    private final  static Logger logger = LoggerFactory.getLogger(BootMqQueneConsumerService.class);


    @Reference(group = "1")
    private CartService cartService;

    @JmsListener(destination = "queue")
    public void receiveStringQueue(String msg) {
        logger.info("====111===");
        System.out.println("接收到消息...." + msg);
    }

    @JmsListener(destination = "queue1")
    public void receiveStringQueue1(ApiResult msg) {
        logger.info("====111===");

        Map map = (Map)msg.getMap().get("param");
        User user = JSON.parseObject(JSON.toJSONString(map),User.class);

        System.out.println(cartService.findCartByUserId(8L));

        System.out.println(user.toString());
        System.out.println(msg.toString());
    }

    @JmsListener(destination = "stringListQueue")
    public void receiveStringListQueue(List<String> list) {
        System.out.println("接收到集合队列消息...." + list);
    }


    @JmsListener(destination = "objQueue")
    public void receiveObjQueue(ObjectMessage objectMessage) throws Exception {
        System.out.println("接收到对象队列消息...." + objectMessage.getObject());
    }


    @JmsListener(destination = "objListQueue")
    public void receiveObjListQueue(ObjectMessage objectMessage) throws Exception {
        System.out.println("接收到的对象队列消息..." + objectMessage.getObject());
    }


}