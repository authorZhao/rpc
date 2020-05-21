package com.git.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author authorZhao
 * @date 2019/12/19
 */
@Slf4j
public class CheckSqlListener implements ApplicationListener<ContextRefreshedEvent> , ApplicationContextAware {



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("正在监听容器的事件：{}",event.getSource().toString());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext.publishEvent(new ContextRefreshedEvent(applicationContext));
        log.info("正在查看容器：{}",applicationContext);
    }
}
