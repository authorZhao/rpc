package com.git.dubboprovider.path;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 获取接口实现类测试
 */
public class Test1 {

    @Test
    public void getCjildClassByInteface() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.git.soc.inter.UserService");
        ServiceLoader<?> loader = ServiceLoader.load(clazz);
        Iterator<?> iterator = loader.iterator();
        while(iterator.hasNext()){
            Class<?> aClass = iterator.next().getClass();
            System.out.println(aClass.getName());
        }
        //测试合并
    }



    @Test
    public void getPath() throws ClassNotFoundException, IOException {
        // 获取当前项目的目录
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);//注意返回的是反斜杠标识的目录名
        // 获取当前类的目录
        URL xmlpath = this.getClass().getClassLoader().getResource("");
        System.out.println(xmlpath);
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);
        // 获取所有的类路径 包括jar包的路径
        System.out.println(System.getProperty("java.class.path"));


    }

}
