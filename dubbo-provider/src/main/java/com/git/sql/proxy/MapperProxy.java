package com.git.sql.proxy;

import com.git.sql.anno.MySelect;
import com.git.sql.util.AnnotationUtil;
import com.git.sql.util.SqlQuery;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class MapperProxy {
    static class MyInvocationHandler implements InvocationHandler {
        /**
         * 接口名
         */
        String interfaceName;

        /**
         * 构造函数
         * @param interfaceName
         */
        public MyInvocationHandler(String interfaceName) {
            super();
            this.interfaceName = interfaceName;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            MySelect annotation = AnnotationUtil.getAnnotation(method, MySelect.class);
            if(annotation==null)throw new RuntimeException("找不着select");
            String sql = annotation.value();
            sql.replace("#{id}",(String)args[0]);
            return SqlQuery.querySql(sql);
            //return proxy;
        }

    }

    public static Object getObject(final Class interfaceInfo) {
        // 获取接口名
        String interfaceName = interfaceInfo.getName();

        ClassLoader classLoader = interfaceInfo.getClassLoader();
        Class[] interfaces = { interfaceInfo };
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(interfaceName);
        //创建代理对象
        Object object = Proxy.newProxyInstance(classLoader, interfaces, myInvocationHandler);
        return object;
    }
}
