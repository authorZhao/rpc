package com.git.config.condition;


import com.git.config.selector.MySelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLogAop {

}
