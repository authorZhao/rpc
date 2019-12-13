package com.git.config.selector;

import com.git.sql.config.MyFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

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
            definition.getPropertyValues().add("serviceInterface", (Object) definition.getBeanClassName());
            definition.setBeanClass(MyFactoryBean.class);
        }
        return beanDefinitions;
    }

   /* protected void registerDefaultFilters() {
        this.addIncludeFilter((TypeFilter) new AnnotationTypeFilter(MyMapperScan.class));
    }*/

    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
