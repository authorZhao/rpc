package com.git.dubboprovider.service.impl;

import com.git.dubboprovider.util.AnnotationUtil;
import com.git.inter.RpcInvoker;
import com.git.model.ApiResult;
import org.apache.dubbo.config.annotation.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

public class RpcInvokerImpl implements RpcInvoker {

    /**
     * 反射接收 类名、方法名、参数
     * @param apiResult
     * @return
     */
    @Override
    public ApiResult invoke(ApiResult apiResult) {
        Map<String, Object> map = apiResult.getMap();
        String className =  (String)map.get("className");
        String methodName =  (String)map.get("methodName");
        Object param = map.get("param");
        Object obj = null;
        try {
            Class clazz = Class.forName(className);

            //Method method = clazz.getMethod(methodName);

            Method[] methods = clazz.getMethods();
            Method method = Arrays.stream(methods).filter(m->m.getName().equals(methodName)).findAny().get();

            Object o = clazz.getConstructors()[0].newInstance();
            obj = method.invoke(o, param);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        apiResult.setObject(obj);
        return apiResult;
    }

    @Override
    public ApiResult asyncInvoke(ApiResult apiResult) {
        Map<String, Object> map = apiResult.getMap();
        String className =  (String)map.get("className");
        String methodName =  (String)map.get("methodName");
        Object param = map.get("param");
        Object obj = null;
        try {
            Class clazz = Class.forName(className);

            //Method method = clazz.getMethod(methodName);

            Method[] methods = clazz.getMethods();
            Method method = Arrays.stream(methods).filter(m->m.getName().equals(methodName)).findAny().get();

            Object o = clazz.getConstructors()[0].newInstance();
            obj = method.invoke(o, param);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        apiResult.setObject(obj);
        return apiResult;
    }
}
