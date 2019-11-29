package com.git.dubboconsumer.boot;

import com.alibaba.fastjson.JSON;
import com.git.inter.UserService;
import com.git.model.ApiResult;
import com.git.model.Role;
import com.git.model.User;
import com.git.service.CartService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class Test1 {

    //@Reference(version = "1.0.0")
    @Reference(group = "1")
    private CartService cartService;

    //Reference(version = "2.0.0")
    @Reference(group = "2")
    private CartService cartService2;

    @Reference(async=true)
    private UserService userService;

    @Test()
    void contextLoads() {
        String api = "{\t\n" +
                "\t\"code\":0,\n" +
                "\t\"msg\":\"测试queue\",\n" +
                "\t\"map\":{\n" +
                "\t\t\"queueName\":\"queue1\",\n" +
                "\t\t\"param\":null,\n" +
                "\t\t\"beanName\":\"产品.git\",\n" +
                "\t\t\"methodName\":\"service\"\n" +
                "\t\t\n" +
                "\t}\n" +
                "\t\n" +
                "}";

        ApiResult apiResult = JSON.parseObject(api,ApiResult.class);
        User user = new User();
        user.setId(18);
        user.setAge(24);
        user.setName("张三");
        user.setSex("男");
        Role role1 = new Role();
        role1.setId(1);
        role1.setName("财务");
        role1.setDesc("发工资的");

        Role role2 = new Role();
        role2.setId(2);
        role2.setName("hr");
        role2.setDesc("招人的");


        Role role3 = new Role();
        role3.setId(3);
        role3.setName("开发");
        role3.setDesc("写代码的");

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);

        user.setRoles(roles);
        apiResult.getMap().put("param",user);
        String aaa = JSON.toJSONString(apiResult);
        System.out.println(aaa);

        System.out.println(cartService.findCartByUserId(8L));
        System.out.println(cartService2.findCartByUserId(8L));

    }

    @Test
    void contextLoad2() {
        User user = new User();
        user.setId(20);
        System.out.println(userService.getUserRoleByUser(user));
        System.out.println("============================结束========================");

    }

}
