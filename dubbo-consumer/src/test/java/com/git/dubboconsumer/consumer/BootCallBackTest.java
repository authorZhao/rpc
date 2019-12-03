package com.git.dubboconsumer.consumer;

import com.git.inter.CallbackListener;
import com.git.inter.CallbackService;
import com.git.inter.RoleService;
import com.git.inter.UserService;
import com.git.model.Role;
import com.git.service.CartService;
import org.apache.dubbo.config.annotation.Argument;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.function.Function;

@SpringBootTest
public class BootCallBackTest {

    //@Reference(version = "1.0.0")
    @Reference(group = "1")
    private CartService cartService;

    //Reference(version = "2.0.0")
    @Reference(group = "2")
    private CartService cartService2;


    @Reference(async=true)
    private UserService userService;

    //(methods = {@Method(name="checkRole2",arguments=@Argument(index = 1))})
    @Reference
    private RoleService roleService;

    @Test

    public void test1(){
        Role role = new Role();
        role.setName("财务");
        Function<Role,Role> function  = r->{
            System.out.println("===================开始修正===================");
            System.out.println("===================修正之前===================");
            System.out.println(r);
            r.setName("管理");
            r.setDesc("搞管理的");
            return r;
        };
        Role role1 = roleService.checkRole2(role,function );
        Role role2 = roleService.checkRole2(role, function);

        System.out.println("===================角色调用完毕===================");
        System.out.println("===================修正之后===================");
        System.out.println(role1);
    }
}
