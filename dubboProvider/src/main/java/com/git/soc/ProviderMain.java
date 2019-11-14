package com.git.soc;

import com.git.soc.inter.UserService;
import com.git.soc.inter.impl.UserServiceImpl;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ServiceLoader;

/**
 * 简单rpc提供者
 */
public class ProviderMain {

    public static void main(String[] args) {
        try {
            System.out.println("start server 20881");
            ServerSocket serverSocket = new ServerSocket(20881);
            while (true) {
                ObjectInputStream objectInputStream = null;
                ObjectOutputStream objectOutputStream = null;
                try {
                    Socket socket = serverSocket.accept();
                    // 1,接收数据
                    InputStream inputStream = socket.getInputStream();
                    objectInputStream = new ObjectInputStream(inputStream);
                    String interfaceName = objectInputStream.readUTF();
                    String methodName = objectInputStream.readUTF();
                    Class[] parameterTypes = (Class[]) objectInputStream.readObject();
                    Object[] argments = (Object[]) objectInputStream.readObject();
                    // 2,执行方法
                    // 根据interfaceName找到实现类
                    // map(interfaceName,impl);
                    Class<?> interfaceClass = Class.forName(interfaceName);
                    ServiceLoader<?> loader = ServiceLoader.load(interfaceClass);
                    Class implClass = UserServiceImpl.class;
                    //Class implClass = null;
                    //if(loader!=null)implClass = null;


                    //Class implClass = Class.forName(interfaceName);
                    Object implObject = implClass.newInstance();
                    //Object implObject = implClass.getDeclaredConstructors()[0].newInstance();
                    Method method = implClass.getMethod(methodName, parameterTypes);
                    Object result = method.invoke(implObject, argments);

                    // 3,返回数据
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
