package com.git.aop;

import com.git.bean.Person;
import com.git.sql.config.SqlConfig;
import com.git.sql.mapper.MyMapper;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Map;

public class Test1 {
    @Test
    public void test1(){
        Person person = new Person();
        person.setName("渣渣辉");

        person.sayHello();

    }
}
