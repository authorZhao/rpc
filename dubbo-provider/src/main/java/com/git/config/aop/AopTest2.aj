package com.git.config.aop;

public aspect AopTest2 {
    pointcut aaa():
            execution(com.git.config.aop.Person2.sayHello());
    before():aaa(){
        System.out.println("Hello");
    }
}
