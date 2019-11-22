package com.git.dubboprovider.path;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 获取接口实现类测试
 */
public class Test2 {


    @Test
    public void getCjildClassByInteface() throws ClassNotFoundException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-provider.xml");
        context.start();
        System.out.println("服务已经启动");
        while(true){

        }


    }



    @Test
    public void getPath() throws ClassNotFoundException, IOException {


    }

}
