package com.git.config.aop;

import com.git.config.condition.EnabelMyAop;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author authorZhao
 * @date 2019/12/12
 */

@Aspect
@EnabelMyAop
public class AopStrengthen {

    //@Pointcut("execution(com.git.bean.Person.sayHello())")
    @Pointcut("execution(* com.git.bean.Person.sayHello())")
    private void pointcut() {}  // signature

    @Before("pointcut()")
    public void before(){
        System.out.println("Hello");
    }
}
