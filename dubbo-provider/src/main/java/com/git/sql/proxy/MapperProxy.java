package com.git.sql.proxy;

import com.git.sql.anno.MySelect;
import com.git.sql.mapper.MyMapper;
import com.git.sql.util.AnnotationUtil;
import com.git.sql.util.OrmUtil;
import com.git.sql.util.SqlQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.sql.ResultSet;

public class MapperProxy {

    private static final Logger logger = LoggerFactory.getLogger(MapperProxy.class);

    private static class MyInvocationHandler implements InvocationHandler {
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
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this,args);
            }
            MySelect annotation = AnnotationUtil.getAnnotation(method, MySelect.class);
            if(annotation==null)throw new RuntimeException("找不着select");
            String sql = annotation.value();
            sql = sql.replace("#{id}",String.valueOf(args[0]));
            ResultSet resultSet =  SqlQuery.querySql(sql);

            Object object = OrmUtil.convertResultSet(resultSet,method.getReturnType());
            return null;
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
        Object object = null;
        try {
            object = Proxy.newProxyInstance(classLoader, interfaces, myInvocationHandler);
        }catch (Exception e){
            System.out.println("获取不到代理对象");
            e.printStackTrace();
        }
        return object;
    }
}
