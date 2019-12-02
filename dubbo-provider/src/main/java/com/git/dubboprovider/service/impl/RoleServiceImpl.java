package com.git.dubboprovider.service.impl;

import com.git.inter.RoleService;
import com.git.model.Role;

import java.util.function.UnaryOperator;

public class RoleServiceImpl implements RoleService {


    @Override
    public Role checkRole(Role role, UnaryOperator<Role> function) {
        if(role==null) throw new RuntimeException("角色不可为空");
        if(role.getName().equals("财务")){
            System.out.println("角色名字应该为为管理员");
            return function.apply(role);
            //role.setName(管理员);
        }
        return role;
    }
}
