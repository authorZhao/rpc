package com.git.soc;

import com.git.inter.UserService;
import com.git.model.User;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * rpc简单消费者
 */
public class ConsumerMain {
    public static void main(String[] args) {
        try {
            //获取代理对象
            Object object = getObject(UserService.class);
            //执行方法
            UserService userService = (UserService) object;

            User user = new User();
            user.setId(2);
            user.setAge(25);
            user.setName("张三");
            user.setSex("男");
            user = userService.getUserRoleByUser(user);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * jdk动态代理
     */
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

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            ObjectOutputStream objectOutputStream = null;
            ObjectInputStream objectInputStream = null;
            try {
                // 1,获取方法名以及参数
                String methodName = method.getName();
                Class[] parameterTypes = method.getParameterTypes();

                // 2,建立连接
                Socket socket = new Socket("127.0.0.1", 20881);
                OutputStream outputStream = socket.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeUTF(interfaceName);
                objectOutputStream.writeUTF(methodName);
                objectOutputStream.writeObject(parameterTypes);
                objectOutputStream.writeObject(args);
                // 3,读取返回的代理对象
                InputStream inputStream = socket.getInputStream();
                objectInputStream = new ObjectInputStream(inputStream);
                Object result = objectInputStream.readObject();
                System.out.println("返回的结果为" + result);

                return result;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                objectOutputStream.close();
                objectInputStream.close();
            }
            return null;
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

