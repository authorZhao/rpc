package com.git.dubboprovider;

import com.git.config.beanname.MyBeanName;
import com.git.config.condition.MyComponentScan;
import com.git.mq.config.ActivemqConfig;
import com.google.errorprone.annotations.MustBeClosed;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@DubboComponentScan({"com.git.dubboprovider.service"})
@Import({ActivemqConfig.class})
@ComponentScan(basePackages = "com.git.mq.controller",nameGenerator = MyBeanName.class)
public class DubboProviderApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(DubboProviderApplication.class, args);


       /* JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        // Send a message with a POJO - the template reuse the message converter
        System.out.println("Sending an email message.");
        jmsTemplate.convertAndSend("mailbox", "haha");*/

    }

}
