package com.git.config.condition;


import com.git.config.selector.MySelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MySelector.class)
public @interface MyComponentScan {
    String[] basePackages() default {};
    String value() default "";
    Class<?>[] classes() default {};
}
