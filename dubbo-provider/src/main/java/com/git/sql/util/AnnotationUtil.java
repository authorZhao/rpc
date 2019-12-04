package com.git.sql.util;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 注解工具类
 */
public class AnnotationUtil {

    /**
     * 获取类的注解
     * @param obj 对象实例
     * @param clazz 目标注解的Class对象
     * @param <T> 目标注解的泛型，必须是注解
     * @return
     */
    public static <T extends Annotation> T getAnnotation(Object obj, Class<T> clazz) {
        if (obj == null) {
            return null;
        }
        return obj.getClass().getAnnotation(clazz);
    }

    /**
     * 获取方法的注解,如果有直接返回，没有找类上面的相同注解
     * @param method 方法
     * @param clazz 目标注解的Class对象
     * @param <T> 目标注解的泛型，必须是注解
     * @return
     */
    public static <T extends Annotation> T getAnnotation(Method method, Class<T> clazz) {
        if (method == null) {
            return null;
        }
        T annotation = method.getAnnotation(clazz);
        if (annotation != null) {
            return annotation;
        }
        //找不到，判断该注解是否能够作用于类
        Target target = clazz.getAnnotation(Target.class);
        if (target == null) {
            return null;
        }
        //判断该注解是否能够作用于类
        boolean canBeInClass = Arrays.stream(target.value()).filter(a->a== ElementType.TYPE).findAny().isPresent();
        if(!canBeInClass)return null;
        //可以的话直接返回类上的相同注解
        return method.getDeclaringClass().getAnnotation(clazz);
    }

    /**
     * 获取方法参数的注解集合,参数上的注解,和参数对应，没有的为null
     * @param method
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Annotation> List<T> getMethodParamAnnotations(Method method, Class<T> clazz) {
        Annotation[][] pas = method.getParameterAnnotations();
        ArrayList<T> result = new ArrayList<>(pas.length);
        for (Annotation[] pa : pas) {
            boolean isAdded = false;
            for (Annotation annot : pa) {
                if (clazz.isInstance(annot)){
                    result.add((T)annot);
                    isAdded = true;
                    break;
                }
            }
            if (isAdded)continue;
            result.add(null);
        }
        return result;
    }
}