package com.git.dubboconsumer.boot;

import com.git.inter.UserService;
import com.git.model.User;
import com.git.service.CartService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Test1 {

    //@Reference(version = "1.0.0")
    @Reference(group = "1")
    private CartService cartService;

    //Reference(version = "2.0.0")
    @Reference(group = "2")
    private CartService cartService2;

    @Reference
    private UserService userService;

    @Test
    void contextLoads() {
        System.out.println(cartService.findCartByUserId(8L));
        System.out.println(cartService2.findCartByUserId(8L));

    }

    @Test
    void contextLoad2() {
        User user = new User();
        user.setId(20);
        System.out.println(userService.getUserRoleByUser(user));

    }

}
