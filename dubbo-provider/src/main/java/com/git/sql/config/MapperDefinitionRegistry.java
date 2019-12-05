package com.git.sql.config;

import com.git.sql.mapper.MyMapper;
import com.git.sql.util.SqlQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MapperDefinitionRegistry implements ApplicationContextAware , BeanDefinitionRegistryPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(MapperDefinitionRegistry.class);
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        Class beanClazz = MyMapper.class;

/*        //使用RootBeanDefinition
        BeanDefinition beanDefinition = new RootBeanDefinition(Car.class);
        //beanDefinition.setAttribute("car",car1);

        registry.registerBeanDefinition("car",beanDefinition);*/




        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClazz);
        GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();

        //在这里，我们可以给该对象的属性注入对应的实例。
        //比如mybatis，就在这里注入了dataSource和sqlSessionFactory，
        // 注意，如果采用definition.getPropertyValues()方式的话，
        // 类似definition.getPropertyValues().add("interfaceType", beanClazz);
        // 则要求在FactoryBean（本应用中即ServiceFactory）提供setter方法，否则会注入失败
        // 如果采用definition.getConstructorArgumentValues()，
        // 则FactoryBean中需要提供包含该属性的构造方法，否则会注入失败
        definition.getConstructorArgumentValues().addGenericArgumentValue(beanClazz);

        //注意，这里的BeanClass是生成Bean实例的工厂，不是Bean本身。
        // FactoryBean是一种特殊的Bean，其返回的对象不是指定类的一个实例，
        // 其返回的是该工厂Bean的getObject方法所返回的对象。
        definition.setBeanClass(MyFactoryBean.class);

        //这里采用的是byType方式注入，类似的还有byName等
        definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
        registry.registerBeanDefinition(beanClazz.getSimpleName(), definition);
        logger.info("{}定义信息注册完毕",beanClazz.getSimpleName());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String str = "开始注册bean";
        System.out.println(str);
    }
}
