package com.git.sql;

import com.git.sql.config.SqlConfig;
import com.git.sql.mapper.MyMapper;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

public class Test1 {
    @Test
    public void test1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SqlConfig.class);
        context.start();
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        MyMapper mapper = context.getBean(MyMapper.class);

        Map goodsById = mapper.getGoodsById(8);


        while(true){

        }


    }
}
