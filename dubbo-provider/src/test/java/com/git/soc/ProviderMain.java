package com.git.soc;

import com.git.dubboprovider.service.impl.UserServiceImpl;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ServiceLoader;

/**
 * 简单rpc生产者
 */
public class ProviderMain {

    public static void main(String[] args) {
        try {
            System.out.println("服务启动，端口号20881");
            ServerSocket serverSocket = new ServerSocket(20881);
            while (true) {
                ObjectInputStream objectInputStream = null;
                ObjectOutputStream objectOutputStream = null;
                try {
                    Socket socket = serverSocket.accept();
                    // 1输入流
                    InputStream inputStream = socket.getInputStream();
                    objectInputStream = new ObjectInputStream(inputStream);
                    //这里每次读的顺序和消费者发送的顺序对应
                    String interfaceName = objectInputStream.readUTF();
                    String methodName = objectInputStream.readUTF();
                    Class[] parameterTypes = (Class[]) objectInputStream.readObject();
                    Object[] argments = (Object[]) objectInputStream.readObject();
                    // 2,获取方法执行
                    // 根据interfaceName获取实现类，可以采取spi实现方式
                    // map(interfaceName,impl);
                    Class<?> interfaceClass = Class.forName(interfaceName);
                    ServiceLoader<?> loader = ServiceLoader.load(interfaceClass);
                    Class implClass = UserServiceImpl.class;//这里写死
                    //Class implClass = null;
                    //if(loader!=null)implClass = null;


                    //Class implClass = Class.forName(interfaceName);
                    Object implObject = implClass.newInstance();
                    //Object implObject = implClass.getDeclaredConstructors()[0].newInstance();
                    Method method = implClass.getMethod(methodName, parameterTypes);
                    Object result = method.invoke(implObject, argments);

                    // 3,写出
                    OutputStream outputStream = socket.getOutputStream();
                    objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(result);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    objectInputStream.close();
                    objectOutputStream.close();
                }

            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}

