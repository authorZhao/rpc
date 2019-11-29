package com.git.mq.controller;

import com.git.model.ApiResult;
import com.git.mq.service.BootMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq/activemq")
public class MqController {

    @Autowired
    private BootMqService mqService;
    @PostMapping("/queue")
    public void sendQueueMsg(@RequestBody ApiResult apiResult){
        mqService.sendQueue(apiResult);
    }

    @PostMapping("/topic")
    public void sendTopicMsg(@RequestBody ApiResult apiResult){
        mqService.sendTopic(apiResult);
    }
}
