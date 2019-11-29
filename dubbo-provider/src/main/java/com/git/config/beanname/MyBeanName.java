package com.git.config.beanname;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

public class MyBeanName extends AnnotationBeanNameGenerator {
    private static final Logger log = LoggerFactory.getLogger(MyBeanName.class);

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        log.info("\r\n======================bean的名称================\r\n"+definition.getBeanClassName()+
                "\r\n================================================="
                );
        return super.generateBeanName(definition, registry);
    }
}
