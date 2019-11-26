package com.git.config.condition;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MqCondition implements Condition {

    @Value("${spring.activemq.broker-url}")
    String url;
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if(url==null)return false;
        return true;
    }
}
