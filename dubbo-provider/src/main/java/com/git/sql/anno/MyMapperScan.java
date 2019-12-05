package com.git.sql.anno;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Import(MapperScan.class)
public @interface MyMapperScan {
    /**
     * 自定义组件扫描，默认类全名
     * @return
     */
    String[] value() default {};
}
