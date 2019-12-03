package com.git.dubboconsumer.consumer;

import com.git.inter.CallbackListener;
import com.git.inter.CallbackService;
import com.git.inter.RoleService;
import com.git.model.Role;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CallBackTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-consumer.xml");
        context.start();

        CallbackService callbackService = (CallbackService) context.getBean("callbackService");

        RoleService roleService =  context.getBean(RoleService.class);

        callbackService.addListener("foo.bar", new CallbackListener(){
            public void changed(String msg) {
                System.out.println("=============================开始回调===========================");
                System.out.println("callback1:" + msg);
                System.out.println("=============================结束回调===========================");
            }
        });
        Role role = new Role();
        role.setName("财务");
       /* UnaryOperator<Role> roleUnaryOperator = r->{
            System.out.println("===================开始修正===================");
            System.out.println("===================修正之前===================");
            System.out.println(r);
            r.setName("管理");
            r.setDesc("搞管理的");
            return r;
        };*/

        System.out.println("===================角色调用开始===================");
        //这个调用失败
      /*  Role role1 = roleService.checkRole(role, r->{
            System.out.println("===================开始修正===================");
            System.out.println("===================修正之前===================");
            System.out.println(r);
            r.setName("管理");
            r.setDesc("搞管理的");
            return r;
        });*/
        Role role1 = roleService.checkRole2(role, r->{
            System.out.println("===================开始修正===================");
            System.out.println("===================修正之前===================");
            System.out.println(r);
            r.setName("管理");
            r.setDesc("搞管理的");
            return r;
        });

        Role role2 = roleService.checkRole3(role1,msg->{
            System.out.println("==============角色名为："+msg+"=================");
        });


        System.out.println("===================角色调用完毕===================");
        System.out.println("===================修正之后===================");
        System.out.println(role1);


    }
}
