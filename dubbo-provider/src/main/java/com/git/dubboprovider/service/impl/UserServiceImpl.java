package com.git.dubboprovider.service.impl;



import com.git.inter.UserService;
import com.git.model.Role;
import com.git.model.User;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {

    @Override
    public User getUserRoleByUser(User user) {
        if(user.getId()==null)throw new RuntimeException("用户id不可为空");
        System.out.println("正在查询数据库");

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
        System.out.println("查询完毕，总共三条结果");
        return user;
    }

    @Override
    public User getUserById(Integer uid) {
        User user = new User();
        user.setId(8);
        return getUserRoleByUser(user);
    }
}
