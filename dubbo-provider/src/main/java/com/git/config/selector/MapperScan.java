package com.git.config.selector;

import com.git.config.condition.MyMapperScan;
import com.git.sql.config.MyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Set;

/**
 * @author authorZhao
 * @date 2019/12/13
 */
public class MapperScan implements ImportBeanDefinitionRegistrar, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(MapperScan.class);
    /**
     * 扫描的包路径
     */
    private String[] basePackage;
    /**
     * 需要扫描的类
     */
    private Class[] classes;

    private ApplicationContext applicationContext;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MyMapperScan.class.getName());
        this.basePackage = (String[])annotationAttributes.get("basePackages");

        this.classes = (Class[])annotationAttributes.get("classes");
        //importingClassMetadata.getMetaAnnotationTypes()
        //名字生成器采用spring默认的,不设置也行
        MySqlMapperScanner mapperScanner = new MySqlMapperScanner(registry);
        //mapperScanner.setBeanNameGenerator(AnnotationBeanNameGenerator.INSTANCE);
        mapperScanner.setResourceLoader(this.applicationContext);
        //mapperScanner.isCandidateComponent()
        //this.scan(StringUtils.tokenizeToStringArray((String) this.basePackage, (String) ",; \t\n"));
        logger.info("开始扫描包：{}",basePackage);
        mapperScanner.doScan(this.basePackage);
        logger.info("开始扫描类：{}",classes);
        if(classes!=null&&classes.length>0){

            for (Class clzz:classes){
                if(!clzz.isInterface())continue;
                if(!registry.containsBeanDefinition(clzz.getSimpleName())){
                    //if(
                    BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clzz);
                    GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
                    definition.getConstructorArgumentValues().addGenericArgumentValue(clzz);
                    definition.setBeanClass(MyFactoryBean.class);
                    //这里采用的是byType方式注入，类似的还有byName等
                    definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
                    registry.registerBeanDefinition(clzz.getSimpleName(), definition);
                    logger.info("{}定义信息注册完毕",clzz.getSimpleName());
                }
            }

        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
