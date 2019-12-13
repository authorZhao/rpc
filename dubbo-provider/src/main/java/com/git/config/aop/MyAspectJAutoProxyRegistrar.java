package com.git.config.aop;

import org.springframework.aop.config.AopConfigUtils;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author authorZhao
 * @date 2019/12/12
 */
public class MyAspectJAutoProxyRegistrar implements ImportBeanDefinitionRegistrar {


    /**
     * import注解注册方法
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(
            AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinition beanDefinition = new RootBeanDefinition(MyAopClass.class);
        System.out.println("容器正在注册MyAopClass" );
        registry.registerBeanDefinition("myAopClass",beanDefinition);
    }
}
