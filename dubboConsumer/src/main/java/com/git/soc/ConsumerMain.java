package com.git.soc;

import com.git.soc.inter.UserService;
import com.git.soc.model.User;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * rpc消费者
 */
public class ConsumerMain {
    public static void main(String[] args) {
        try {
            Object object = getObject(UserService.class);
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

    static class MyInvocationHandler implements InvocationHandler {

        String interfaceName;

        public MyInvocationHandler(String interfaceName) {
            super();
            this.interfaceName = interfaceName;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            ObjectOutputStream objectOutputStream = null;
            ObjectInputStream objectInputStream = null;
            try {
                // 1,得方法名，参数数据
                String methodName = method.getName();
                Class[] parameterTypes = method.getParameterTypes();

                // 2,发送数据
                Socket socket = new Socket("127.0.0.1", 20881);
                OutputStream outputStream = socket.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeUTF(interfaceName);
                objectOutputStream.writeUTF(methodName);
                objectOutputStream.writeObject(parameterTypes);
                objectOutputStream.writeObject(args);
                // 3,接收数据
                InputStream inputStream = socket.getInputStream();
                objectInputStream = new ObjectInputStream(inputStream);
                Object result = objectInputStream.readObject();
                System.out.println("收到远程方法执行结果" + result);

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
        // 1.将本地的接口调用转换成JDK的动态代理，在动态代理中实现接口的远程调用
        String interfaceName = interfaceInfo.getName();
        ClassLoader classLoader = interfaceInfo.getClassLoader();
        Class[] interfaces = { interfaceInfo };
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(interfaceName);
        Object object = Proxy.newProxyInstance(classLoader, interfaces, myInvocationHandler);
        return object;
    }
}
