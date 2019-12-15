package com.git.config.selector;

import com.git.sql.config.MyFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.filter.TypeFilter;

import java.util.Set;

/**
 * @author authorZhao
 * @date 2019/12/13
 */
public class MySqlMapperScanner extends ClassPathBeanDefinitionScanner {

    public MySqlMapperScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitions) {
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            //definition.getPropertyValues().add("serviceInterface", (Object) definition.getBeanClassName());
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            constructorArgumentValues.addIndexedArgumentValue(0,definition.getBeanClassName());
            definition.getConstructorArgumentValues().addArgumentValues(constructorArgumentValues);
            definition.setBeanClass(MyFactoryBean.class);
        }
        return beanDefinitions;
    }

    protected void registerDefaultFilters() {
        this.addIncludeFilter((metadataReader,metadataReaderFactory)->{
            if(metadataReader.getClassMetadata().isInterface())return true;
            return false;
        });
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
