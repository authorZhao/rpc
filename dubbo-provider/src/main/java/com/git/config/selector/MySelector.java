package com.git.config.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MySelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        importingClassMetadata.getAnnotations().stream().forEach(System.out::println);
        return new String[]{"com.git.mq.config.ActivemqConfig"};
    }
}
