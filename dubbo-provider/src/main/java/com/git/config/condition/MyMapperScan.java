package com.git.config.condition;

import com.git.config.selector.MapperScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MapperScan.class)
public @interface MyMapperScan {
    String[] basePackages() default {};
    Class<?>[] classes() default {};
}
