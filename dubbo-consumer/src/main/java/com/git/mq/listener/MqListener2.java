/*
package com.git.mq.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MqListener2 implements ApplicationListener<ContextRefreshedEvent> {

    private static AtomicBoolean isRunned = new AtomicBoolean(false);

    public MqListener2() {
    }

    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null && isRunned.compareAndSet(false, true)) {
            this.runOnce(event.getApplicationContext());
        }

    }

    protected void runOnce(ApplicationContext appContext) {
        MessageConsumerRegister.registerAll(appContext);
    }
}*/
